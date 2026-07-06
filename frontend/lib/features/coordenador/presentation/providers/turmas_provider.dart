import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/turma_model.dart';
import '../../data/repositories/coordenador_repository_impl.dart';

final turmasProvider = FutureProvider<List<TurmaModel>>((ref) async {
  final repository = ref.watch(coordenadorRepositoryProvider);
  return repository.getTurmas();
});
