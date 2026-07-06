import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../data/repositories/aluno_repository_impl.dart';
import '../../data/models/situacao_aluno_turma_model.dart';
import '../../../auth/presentation/providers/auth_provider.dart';


final situacaoAlunoProvider = FutureProvider.autoDispose<List<SituacaoAlunoTurmaModel>>((ref) async {
  final authState = ref.watch(authProvider);
  final alunoId = authState.userId;
  
  if (alunoId == null || alunoId.isEmpty) {
    throw Exception('ID do Aluno não encontrado. Faça login novamente.');
  }

  final repository = ref.watch(alunoRepositoryProvider);
  return repository.getSituacaoAluno(alunoId);
});
