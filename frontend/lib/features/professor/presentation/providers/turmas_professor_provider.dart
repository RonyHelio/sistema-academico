import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/turma_model.dart';
import '../../../auth/presentation/providers/auth_provider.dart';
import '../../data/professor_repository.dart';

final turmasProfessorProvider = FutureProvider<List<TurmaModel>>((ref) async {
  final authState = ref.watch(authProvider);
  final userId = authState.userId;
  
  if (userId == null) {
    return [];
  }
  
  final repository = ref.watch(professorRepositoryProvider);
  return repository.getTurmas(userId);
});
