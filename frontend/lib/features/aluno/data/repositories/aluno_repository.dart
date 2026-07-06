import '../models/situacao_aluno_turma_model.dart';

abstract class AlunoRepository {
  Future<List<SituacaoAlunoTurmaModel>> getSituacaoAluno(String alunoId);
}
