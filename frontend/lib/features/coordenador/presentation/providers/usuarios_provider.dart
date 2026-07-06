import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/usuario_model.dart';
import '../../data/repositories/coordenador_repository_impl.dart';

final usuariosProvider = FutureProvider<List<UsuarioModel>>((ref) async {
  final repository = ref.watch(coordenadorRepositoryProvider);
  return repository.getUsuarios();
});
