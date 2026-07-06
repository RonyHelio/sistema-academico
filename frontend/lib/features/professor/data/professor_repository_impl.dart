import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../core/network/api_client.dart';
import '../../../data/models/turma_model.dart';
import '../../../data/models/matricula_turma_model.dart';
import 'professor_repository.dart';

class ProfessorRepositoryImpl implements ProfessorRepository {
  final Ref _ref;

  ProfessorRepositoryImpl(this._ref);

  Dio get _api => _ref.read(apiClientProvider);

  @override
  Future<List<TurmaModel>> getTurmas(String professorId) async {
    final response = await _api.get('/professores/$professorId/turmas');
    return (response.data as List)
        .map((json) => TurmaModel.fromJson(json))
        .toList();
  }

  @override
  Future<List<MatriculaTurmaModel>> getAlunos(String professorId, int turmaId) async {
    final response = await _api.get('/professores/$professorId/turmas/$turmaId/alunos');
    return (response.data as List)
        .map((json) => MatriculaTurmaModel.fromJson(json))
        .toList();
  }
}
