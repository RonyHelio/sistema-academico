import 'dart:async';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../data/chat_repository_impl.dart';
import '../domain/mensagem_chat.dart';
import '../../auth/presentation/providers/auth_provider.dart';

final chatProvider = StateNotifierProvider.family<ChatNotifier, AsyncValue<List<MensagemChat>>, int>((ref, turmaId) {
  return ChatNotifier(ref, turmaId);
});

class ChatNotifier extends StateNotifier<AsyncValue<List<MensagemChat>>> {
  final Ref _ref;
  final int _turmaId;
  Timer? _timer;

  ChatNotifier(this._ref, this._turmaId) : super(const AsyncValue.loading()) {
    _loadInitialMessages();
    _startPolling();
  }

  void _startPolling() {
    _timer = Timer.periodic(const Duration(seconds: 5), (_) {
      _fetchMessages();
    });
  }

  @override
  void dispose() {
    _timer?.cancel();
    super.dispose();
  }

  Future<void> _loadInitialMessages() async {
    state = const AsyncValue.loading();
    await _fetchMessages();
  }

  Future<void> _fetchMessages() async {
    try {
      final repository = _ref.read(chatRepositoryProvider);
      final newMessages = await repository.getMensagens(_turmaId);
      
      if (mounted) {
        final currentMessages = state.value ?? [];
        
        // Deduplicate messages by ID, keeping the latest versions from the server
        final Map<int, MensagemChat> messageMap = {};
        for (var msg in currentMessages) {
          if (msg.id != null) messageMap[msg.id!] = msg;
        }
        for (var msg in newMessages) {
          if (msg.id != null) messageMap[msg.id!] = msg;
        }
        
        final deduplicatedMessages = messageMap.values.toList()
          ..sort((a, b) => (a.dataEnvio ?? DateTime.now()).compareTo(b.dataEnvio ?? DateTime.now()));

        state = AsyncValue.data(deduplicatedMessages);
      }
    } catch (e, st) {
      if (state.value == null && mounted) {
        state = AsyncValue.error(e, st);
      }
    }
  }

  Future<void> sendMessage(String text) async {
    final authState = _ref.read(authProvider);
    if (!authState.isAuthenticated || authState.userId == null) return;

    final userId = int.tryParse(authState.userId!) ?? 0;

    final newMessage = MensagemChat(
      chatTurmaId: _turmaId,
      remetenteId: userId,
      texto: text,
      dataEnvio: DateTime.now(),
      id: DateTime.now().millisecondsSinceEpoch, // Temporary ID for optimistic update
      nomeRemetente: 'Eu', // Optimistic name
    );

    // Optimistic update
    final currentMessages = state.value ?? [];
    state = AsyncValue.data([...currentMessages, newMessage]);

    try {
      final repository = _ref.read(chatRepositoryProvider);
      final savedMessage = await repository.enviarMensagem(newMessage);
      
      // Replace optimistic message with actual message
      if (mounted) {
        final messages = state.value ?? [];
        final updatedMessages = messages.map((m) {
          if (m.id == newMessage.id) {
            return savedMessage;
          }
          return m;
        }).toList();
        state = AsyncValue.data(updatedMessages);
      }
    } catch (e) {
      // Revert optimistic update
      if (mounted) {
        final messages = state.value ?? [];
        messages.removeWhere((m) => m.id == newMessage.id);
        state = AsyncValue.data([...messages]);
      }
    }
  }

  Future<void> deleteMessage(int messageId) async {
    // Optimistic delete (status 'I')
    final currentMessages = state.value ?? [];
    final originalMessages = List<MensagemChat>.from(currentMessages);

    if (mounted) {
      state = AsyncValue.data(currentMessages.map((m) {
        if (m.id == messageId) {
          return m.copyWith(status: 'I');
        }
        return m;
      }).toList());
    }

    try {
      final repository = _ref.read(chatRepositoryProvider);
      await repository.inativarMensagem(messageId);
    } catch (e) {
      // Revert optimistic delete on error
      if (mounted) {
        state = AsyncValue.data(originalMessages);
      }
    }
  }
}
