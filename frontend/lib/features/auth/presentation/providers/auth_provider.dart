import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../data/auth_state.dart';

class AuthNotifier extends Notifier<AuthState> {
  @override
  AuthState build() {
    return const AuthState();
  }

  void login(Perfil perfil, String userId) {
    state = AuthState(
      isAuthenticated: true,
      perfil: perfil,
      userId: userId,
    );
  }

  void logout() {
    state = const AuthState(isAuthenticated: false);
  }
}

final authProvider = NotifierProvider<AuthNotifier, AuthState>(() {
  return AuthNotifier();
});
