import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../core/network/api_client.dart';
import '../../../data/models/falta_model.dart';
import 'falta_repository.dart';

class FaltaRepositoryImpl implements FaltaRepository {
  final Ref _ref;
  FaltaRepositoryImpl(this._ref);

  Dio get _api => _ref.read(apiClientProvider);

  @override
  Future<FaltaModel> salvarFalta(Map<String, dynamic> faltaData, String usuarioId) async {
    final response = await _api.post(
      '/faltas',
      data: faltaData,
      options: Options(headers: {'usuario-id': usuarioId}),
    );
    return FaltaModel.fromJson(response.data);
  }
}
