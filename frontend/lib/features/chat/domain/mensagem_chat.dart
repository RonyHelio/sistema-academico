class MensagemChat {
  final int? id;
  final int chatTurmaId;
  final int remetenteId;
  final String? nomeRemetente;
  final String texto;
  final DateTime? dataEnvio;
  final String? status;

  MensagemChat({
    this.id,
    required this.chatTurmaId,
    required this.remetenteId,
    this.nomeRemetente,
    required this.texto,
    this.dataEnvio,
    this.status,
  });

  bool get isInactive => status == 'I';

  factory MensagemChat.fromJson(Map<String, dynamic> json) {
    return MensagemChat(
      id: json['id'],
      chatTurmaId: json['chatTurmaId'],
      remetenteId: json['remetenteId'],
      nomeRemetente: json['nomeRemetente'],
      texto: json['texto'] ?? '',
      dataEnvio: json['dataEnvio'] != null
          ? DateTime.parse(json['dataEnvio'])
          : null,
      status: json['status'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'chatTurmaId': chatTurmaId,
      'remetenteId': remetenteId,
      'nomeRemetente': nomeRemetente,
      'texto': texto,
      'dataEnvio': dataEnvio?.toIso8601String(),
      'status': status,
    };
  }

  MensagemChat copyWith({
    int? id,
    int? chatTurmaId,
    int? remetenteId,
    String? nomeRemetente,
    String? texto,
    DateTime? dataEnvio,
    String? status,
  }) {
    return MensagemChat(
      id: id ?? this.id,
      chatTurmaId: chatTurmaId ?? this.chatTurmaId,
      remetenteId: remetenteId ?? this.remetenteId,
      nomeRemetente: nomeRemetente ?? this.nomeRemetente,
      texto: texto ?? this.texto,
      dataEnvio: dataEnvio ?? this.dataEnvio,
      status: status ?? this.status,
    );
  }
}
