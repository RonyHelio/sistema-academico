import 'package:freezed_annotation/freezed_annotation.dart';

part 'matricula_turma_model.freezed.dart';
part 'matricula_turma_model.g.dart';

@freezed
class MatriculaTurmaModel with _$MatriculaTurmaModel {
  const MatriculaTurmaModel._();

  const factory MatriculaTurmaModel({
    int? id,
    int? alunoId,
    String? nomeAluno,
    int? turmaId,
    String? descricaoTurma,
    String? nomeDisciplina,
    String? status,
  }) = _MatriculaTurmaModel;

  factory MatriculaTurmaModel.fromJson(Map<String, dynamic> json) => _$MatriculaTurmaModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
