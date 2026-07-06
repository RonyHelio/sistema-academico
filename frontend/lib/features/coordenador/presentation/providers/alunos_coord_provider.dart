import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/aluno_model.dart';
import '../../data/repositories/coordenador_repository_impl.dart';

final alunosCoordProvider = FutureProvider<List<AlunoModel>>((ref) async {
  final repository = ref.watch(coordenadorRepositoryProvider);
  return repository.getAlunos();
});
