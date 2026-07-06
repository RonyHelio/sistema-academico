import 'package:freezed_annotation/freezed_annotation.dart';

part 'situacao_aluno_turma_model.freezed.dart';
part 'situacao_aluno_turma_model.g.dart';

@freezed
class SituacaoAlunoTurmaModel with _$SituacaoAlunoTurmaModel {
  const factory SituacaoAlunoTurmaModel({
    int? turmaId,
    String? nomeDisciplina,
    String? descricaoTurma,
    String? periodoLetivo,
    double? mediaNotas,
    int? totalFaltas,
    String? situacao,
  }) = _SituacaoAlunoTurmaModel;

  factory SituacaoAlunoTurmaModel.fromJson(Map<String, dynamic> json) => _$SituacaoAlunoTurmaModelFromJson(json);
}
