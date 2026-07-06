class SituacaoAlunoTurmaModel {
  final int turmaId;
  final String nomeDisciplina;
  final String descricaoTurma;
  final String periodoLetivo;
  final double mediaNotas;
  final int totalFaltas;
  final String situacao;

  SituacaoAlunoTurmaModel({
    required this.turmaId,
    required this.nomeDisciplina,
    required this.descricaoTurma,
    required this.periodoLetivo,
    required this.mediaNotas,
    required this.totalFaltas,
    required this.situacao,
  });

  factory SituacaoAlunoTurmaModel.fromJson(Map<String, dynamic> json) {
    return SituacaoAlunoTurmaModel(
      turmaId: json['turmaId'] ?? 0,
      nomeDisciplina: json['nomeDisciplina'] ?? '',
      descricaoTurma: json['descricaoTurma'] ?? '',
      periodoLetivo: json['periodoLetivo'] ?? '',
      mediaNotas: (json['mediaNotas'] as num?)?.toDouble() ?? 0.0,
      totalFaltas: json['totalFaltas'] ?? 0,
      situacao: json['situacao'] ?? 'DESCONHECIDO',
    );
  }
}
