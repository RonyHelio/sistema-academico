import '../../../data/models/falta_model.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'falta_repository_impl.dart';

abstract class FaltaRepository {
  Future<FaltaModel> salvarFalta(Map<String, dynamic> faltaData, String usuarioId);
}

final faltaRepositoryProvider = Provider<FaltaRepository>((ref) {
  return FaltaRepositoryImpl(ref);
});
