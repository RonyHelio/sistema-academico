import '../../../data/models/turma_model.dart';
import '../../../data/models/matricula_turma_model.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'professor_repository_impl.dart';

abstract class ProfessorRepository {
  Future<List<TurmaModel>> getTurmas(String professorId);
  Future<List<MatriculaTurmaModel>> getAlunos(String professorId, int turmaId);
}

final professorRepositoryProvider = Provider<ProfessorRepository>((ref) {
  return ProfessorRepositoryImpl(ref);
});
