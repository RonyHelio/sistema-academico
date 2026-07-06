import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'chat_provider.dart';
import 'widgets/chat_bubble.dart';
import 'widgets/chat_input.dart';

class ChatPage extends ConsumerStatefulWidget {
  final int turmaId;

  const ChatPage({super.key, required this.turmaId});

  @override
  ConsumerState<ChatPage> createState() => _ChatPageState();
}

class _ChatPageState extends ConsumerState<ChatPage> {
  final ScrollController _scrollController = ScrollController();

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  void _scrollToBottom() {
    if (_scrollController.hasClients) {
      _scrollController.animateTo(
        0.0,
        duration: const Duration(milliseconds: 300),
        curve: Curves.easeOut,
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    final chatState = ref.watch(chatProvider(widget.turmaId));

    // Listen to state changes to auto-scroll when new messages arrive
    ref.listen(chatProvider(widget.turmaId), (previous, next) {
      if (next.hasValue && previous?.value?.length != next.value?.length) {
        WidgetsBinding.instance.addPostFrameCallback((_) => _scrollToBottom());
      }
    });

    final screenWidth = MediaQuery.of(context).size.width;
    final isDesktop = screenWidth > 600;

    Widget chatContent = Column(
      children: [
        Expanded(
          child: chatState.when(
            data: (messages) {
              if (messages.isEmpty) {
                return const Center(child: Text('Nenhuma mensagem ainda.'));
              }
              // Reverse the list to work with reverse: true in ListView
              final reversedMessages = messages.reversed.toList();
              
              return ListView.builder(
                controller: _scrollController,
                reverse: true,
                padding: const EdgeInsets.symmetric(vertical: 16.0),
                itemCount: reversedMessages.length,
                itemBuilder: (context, index) {
                  final message = reversedMessages[index];
                  return ChatBubble(
                    message: message,
                    onDelete: () {
                      if (message.id != null) {
                        ref.read(chatProvider(widget.turmaId).notifier).deleteMessage(message.id!);
                      }
                    },
                  );
                },
              );
            },
            loading: () => const Center(child: CircularProgressIndicator()),
            error: (error, _) => Center(child: Text('Erro ao carregar mensagens: $error')),
          ),
        ),
        ChatInput(
          onSend: (text) {
            ref.read(chatProvider(widget.turmaId).notifier).sendMessage(text);
          },
        ),
      ],
    );

    if (isDesktop) {
      return Scaffold(
        backgroundColor: Theme.of(context).colorScheme.surfaceVariant.withOpacity(0.3),
        appBar: AppBar(
          title: const Text('Chat da Turma'),
        ),
        body: Row(
          children: [
            Expanded(
              child: Center(
                child: Text(
                  'Selecione uma turma para conversar',
                  style: Theme.of(context).textTheme.headlineMedium?.copyWith(
                        color: Theme.of(context).colorScheme.onSurface.withOpacity(0.5),
                      ),
                ),
              ),
            ),
            Container(
              width: 400,
              decoration: BoxDecoration(
                color: Theme.of(context).colorScheme.surface,
                border: Border(
                  left: BorderSide(
                    color: Theme.of(context).dividerColor,
                    width: 1,
                  ),
                ),
              ),
              child: chatContent,
            ),
          ],
        ),
      );
    }

    return Scaffold(
      appBar: AppBar(
        title: const Text('Chat da Turma'),
      ),
      body: chatContent,
    );
  }
}
