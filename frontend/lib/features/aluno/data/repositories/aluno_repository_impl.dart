import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/situacao_aluno_turma_model.dart';
import 'aluno_repository.dart';

class AlunoRepositoryImpl implements AlunoRepository {
  final String baseUrl;

  AlunoRepositoryImpl({this.baseUrl = 'http://localhost:8080'});

  @override
  Future<List<SituacaoAlunoTurmaModel>> getSituacaoAluno(String alunoId) async {
    final url = Uri.parse('$baseUrl/api/alunos/$alunoId/situacao');
    
    final response = await http.get(url, headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    });

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(utf8.decode(response.bodyBytes));
      return data.map((item) => SituacaoAlunoTurmaModel.fromJson(item)).toList();
    } else {
      throw Exception('Falha ao carregar situação do aluno: ${response.statusCode}');
    }
  }
}
