import '../../../data/models/nota_model.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'nota_repository_impl.dart';

abstract class NotaRepository {
  Future<NotaModel> salvarNota(Map<String, dynamic> notaData, String usuarioId);
}

final notaRepositoryProvider = Provider<NotaRepository>((ref) {
  return NotaRepositoryImpl(ref);
});
