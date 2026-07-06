import 'package:flutter/foundation.dart';

enum Perfil { aluno, professor, admin }

@immutable
class AuthState {
  final bool isAuthenticated;
  final Perfil? perfil;
  final String? userId;
  
  const AuthState({
    this.isAuthenticated = false,
    this.perfil,
    this.userId,
  });

  AuthState copyWith({
    bool? isAuthenticated,
    Perfil? perfil,
    String? userId,
  }) {
    return AuthState(
      isAuthenticated: isAuthenticated ?? this.isAuthenticated,
      perfil: perfil ?? this.perfil,
      userId: userId ?? this.userId,
    );
  }
}
