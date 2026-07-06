enum Perfil {
  admin,
  professor,
  aluno;

  String get label {
    switch (this) {
      case Perfil.admin:
        return 'Administrador';
      case Perfil.professor:
        return 'Professor';
      case Perfil.aluno:
        return 'Aluno';
    }
  }

  static Perfil fromString(String value) {
    switch (value.toLowerCase()) {
      case 'admin':
      case 'administrador':
      case 'coordenador':
        return Perfil.admin;
      case 'professor':
        return Perfil.professor;
      case 'aluno':
        return Perfil.aluno;
      default:
        throw ArgumentError('Perfil inválido: $value');
    }
  }
}
