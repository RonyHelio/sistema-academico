import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/curso_model.dart';
import '../../data/repositories/coordenador_repository_impl.dart';

final cursosProvider = FutureProvider<List<CursoModel>>((ref) async {
  final repository = ref.watch(coordenadorRepositoryProvider);
  return repository.getCursos();
});
