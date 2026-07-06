import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../core/network/api_client.dart';
import '../domain/mensagem_chat.dart';
import 'chat_repository.dart';

import '../../auth/presentation/providers/auth_provider.dart';

final chatRepositoryProvider = Provider<ChatRepository>((ref) {
  final userId = ref.watch(authProvider).userId;
  return ChatRepositoryImpl(ref.watch(apiClientProvider), userId);
});

class ChatRepositoryImpl implements ChatRepository {
  final Dio _dio;
  final String? _userId;

  ChatRepositoryImpl(this._dio, this._userId);

  Options _getOptions() {
    return Options(
      headers: {
        if (_userId != null) 'usuario-id': _userId,
      },
    );
  }

  @override
  Future<List<MensagemChat>> getMensagens(int chatTurmaId) async {
    final response = await _dio.get('/mensagens/chat/$chatTurmaId', options: _getOptions());
    return (response.data as List)
        .map((e) => MensagemChat.fromJson(e))
        .toList();
  }

  @override
  Future<MensagemChat> enviarMensagem(MensagemChat mensagem) async {
    final response = await _dio.post(
      '/mensagens',
      data: {
        'chatTurmaId': mensagem.chatTurmaId,
        'remetenteId': mensagem.remetenteId,
        'texto': mensagem.texto,
      },
      options: _getOptions(),
    );
    return MensagemChat.fromJson(response.data);
  }

  @override
  Future<void> inativarMensagem(int id) async {
    await _dio.delete('/mensagens/$id', options: _getOptions());
  }
}
