import '../domain/mensagem_chat.dart';

abstract class ChatRepository {
  Future<List<MensagemChat>> getMensagens(int chatTurmaId);
  Future<MensagemChat> enviarMensagem(MensagemChat mensagem);
  Future<void> inativarMensagem(int id);
}
