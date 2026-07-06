import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/disciplina_model.dart';
import '../../data/repositories/coordenador_repository_impl.dart';

final disciplinasProvider = FutureProvider<List<DisciplinaModel>>((ref) async {
  final repository = ref.watch(coordenadorRepositoryProvider);
  return repository.getDisciplinas();
});
