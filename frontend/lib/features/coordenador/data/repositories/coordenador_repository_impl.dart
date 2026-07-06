import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../core/network/api_client.dart';
import '../../../data/models/aluno_model.dart';
import '../../../data/models/curso_model.dart';
import '../../../data/models/disciplina_model.dart';
import '../../../data/models/matricula_turma_model.dart';
import '../../../data/models/periodo_letivo_model.dart';
import '../../../data/models/turma_model.dart';
import '../../../data/models/usuario_model.dart';
import 'coordenador_repository.dart';

final coordenadorRepositoryProvider = Provider<CoordenadorRepository>((ref) {
  final dio = ref.watch(apiClientProvider);
  return CoordenadorRepositoryImpl(dio);
});

class CoordenadorRepositoryImpl implements CoordenadorRepository {
  final Dio dio;

  CoordenadorRepositoryImpl(this.dio);

  @override
  Future<List<CursoModel>> getCursos() async {
    final response = await dio.get('/cursos');
    return (response.data as List).map((e) => CursoModel.fromJson(e)).toList();
  }

  @override
  Future<void> deleteCurso(int id) async {
    await dio.delete('/cursos/$id');
  }

  @override
  Future<List<DisciplinaModel>> getDisciplinas() async {
    final response = await dio.get('/disciplinas');
    return (response.data as List).map((e) => DisciplinaModel.fromJson(e)).toList();
  }

  @override
  Future<void> deleteDisciplina(int id) async {
    await dio.delete('/disciplinas/$id');
  }

  @override
  Future<List<PeriodoLetivoModel>> getPeriodos() async {
    final response = await dio.get('/periodos-letivos');
    return (response.data as List).map((e) => PeriodoLetivoModel.fromJson(e)).toList();
  }

  @override
  Future<void> deletePeriodo(int id) async {
    await dio.delete('/periodos-letivos/$id');
  }

  @override
  Future<List<TurmaModel>> getTurmas() async {
    final response = await dio.get('/turmas');
    return (response.data as List).map((e) => TurmaModel.fromJson(e)).toList();
  }

  @override
  Future<void> deleteTurma(int id) async {
    await dio.delete('/turmas/$id');
  }

  @override
  Future<List<AlunoModel>> getAlunos() async {
    final response = await dio.get('/alunos');
    return (response.data as List).map((e) => AlunoModel.fromJson(e)).toList();
  }

  @override
  Future<void> deleteAluno(int id) async {
    await dio.delete('/alunos/$id');
  }

  @override
  Future<List<UsuarioModel>> getUsuarios() async {
    final response = await dio.get('/usuarios');
    return (response.data as List).map((e) => UsuarioModel.fromJson(e)).toList();
  }

  @override
  Future<void> deleteUsuario(int id) async {
    await dio.delete('/usuarios/$id');
  }

  @override
  Future<List<MatriculaTurmaModel>> getMatriculas() async {
    final response = await dio.get('/matriculas');
    return (response.data as List).map((e) => MatriculaTurmaModel.fromJson(e)).toList();
  }

  @override
  Future<void> deleteMatricula(int id) async {
    await dio.delete('/matriculas/$id');
  }
}
