import '../../../data/models/curso_model.dart';
import '../../../data/models/disciplina_model.dart';
import '../../../data/models/periodo_letivo_model.dart';
import '../../../data/models/turma_model.dart';
import '../../../data/models/aluno_model.dart';
import '../../../data/models/usuario_model.dart';
import '../../../data/models/matricula_turma_model.dart';

abstract class CoordenadorRepository {
  Future<List<CursoModel>> getCursos();
  Future<void> deleteCurso(int id);
  
  Future<List<DisciplinaModel>> getDisciplinas();
  Future<void> deleteDisciplina(int id);
  
  Future<List<PeriodoLetivoModel>> getPeriodos();
  Future<void> deletePeriodo(int id);
  
  Future<List<TurmaModel>> getTurmas();
  Future<void> deleteTurma(int id);
  
  Future<List<AlunoModel>> getAlunos();
  Future<void> deleteAluno(int id);
  
  Future<List<UsuarioModel>> getUsuarios();
  Future<void> deleteUsuario(int id);
  
  Future<List<MatriculaTurmaModel>> getMatriculas();
  Future<void> deleteMatricula(int id);
}
