import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../core/network/api_client.dart';
import '../../../data/models/nota_model.dart';
import 'nota_repository.dart';

class NotaRepositoryImpl implements NotaRepository {
  final Ref _ref;
  NotaRepositoryImpl(this._ref);

  Dio get _api => _ref.read(apiClientProvider);

  @override
  Future<NotaModel> salvarNota(Map<String, dynamic> notaData, String usuarioId) async {
    final response = await _api.post(
      '/notas',
      data: notaData,
      options: Options(headers: {'usuario-id': usuarioId}),
    );
    return NotaModel.fromJson(response.data);
  }
}
