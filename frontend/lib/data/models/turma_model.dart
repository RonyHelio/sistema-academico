import 'package:freezed_annotation/freezed_annotation.dart';

part 'turma_model.freezed.dart';
part 'turma_model.g.dart';

@freezed
class TurmaModel with _$TurmaModel {
  const TurmaModel._();

  const factory TurmaModel({
    int? id,
    String? nomeDisciplina,
    String? nomeProfessor,
    int? periodoLetivoId,
    String? periodoLetivoDescricao,
    String? descricao,
    String? status,
  }) = _TurmaModel;

  factory TurmaModel.fromJson(Map<String, dynamic> json) => _$TurmaModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
