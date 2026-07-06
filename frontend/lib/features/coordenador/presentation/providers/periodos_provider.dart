import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/periodo_letivo_model.dart';
import '../../data/repositories/coordenador_repository_impl.dart';

final periodosProvider = FutureProvider<List<PeriodoLetivoModel>>((ref) async {
  final repository = ref.watch(coordenadorRepositoryProvider);
  return repository.getPeriodos();
});
