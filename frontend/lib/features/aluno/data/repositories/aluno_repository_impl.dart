import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../core/network/api_client.dart';
import '../models/situacao_aluno_turma_model.dart';
import 'aluno_repository.dart';

final alunoRepositoryProvider = Provider<AlunoRepository>((ref) {
  final dio = ref.watch(apiClientProvider);
  return AlunoRepositoryImpl(dio);
});

class AlunoRepositoryImpl implements AlunoRepository {
  final Dio _dio;

  AlunoRepositoryImpl(this._dio);

  @override
  Future<List<SituacaoAlunoTurmaModel>> getSituacaoAluno(String alunoId) async {
    final response = await _dio.get('/alunos/$alunoId/situacao');
    return (response.data as List)
        .map((item) => SituacaoAlunoTurmaModel.fromJson(item))
        .toList();
  }
}
