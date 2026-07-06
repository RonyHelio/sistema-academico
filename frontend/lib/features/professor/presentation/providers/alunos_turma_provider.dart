import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/matricula_turma_model.dart';
import '../../../auth/presentation/providers/auth_provider.dart';
import '../../data/professor_repository.dart';

final alunosTurmaProvider = FutureProvider.family<List<MatriculaTurmaModel>, int>((ref, turmaId) async {
  final authState = ref.watch(authProvider);
  final userId = authState.userId;
  
  if (userId == null) {
    return [];
  }
  
  final repository = ref.watch(professorRepositoryProvider);
  return repository.getAlunos(userId, turmaId);
});
