import 'package:freezed_annotation/freezed_annotation.dart';

part 'mensagem_chat_model.freezed.dart';
part 'mensagem_chat_model.g.dart';

@freezed
class MensagemChatModel with _$MensagemChatModel {
  const MensagemChatModel._();

  const factory MensagemChatModel({
    int? id,
    int? chatTurmaId,
    int? remetenteId,
    String? nomeRemetente,
    String? texto,
    DateTime? dataEnvio,
    String? status,
  }) = _MensagemChatModel;

  factory MensagemChatModel.fromJson(Map<String, dynamic> json) => _$MensagemChatModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
