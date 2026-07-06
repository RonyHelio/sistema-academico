import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import '../features/auth/presentation/providers/auth_provider.dart';
import '../features/auth/presentation/pages/login_page.dart';
import '../features/chat/presentation/chat_page.dart';

// Placeholder pages for routes that might not exist yet
class PlaceholderPage extends StatelessWidget {
  final String title;
  const PlaceholderPage({super.key, required this.title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(title)),
      body: Center(child: Text('Bem-vindo: $title')),
    );
  }
}

final routerProvider = Provider<GoRouter>((ref) {
  final authState = ref.watch(authProvider);

  return GoRouter(
    initialLocation: '/login',
    redirect: (context, state) {
      final isLoggingIn = state.uri.path == '/login';
      final isLoggedIn = authState.isAuthenticated;

      if (!isLoggedIn && !isLoggingIn) {
        return '/login';
      }

      if (isLoggedIn && isLoggingIn) {
        // Redirect based on role if needed, or to a default dashboard
        return '/aluno'; // Change as appropriate
      }

      return null;
    },
    routes: [
      GoRoute(
        path: '/login',
        builder: (context, state) => const LoginPage(),
      ),
      GoRoute(
        path: '/aluno',
        builder: (context, state) => const PlaceholderPage(title: 'Área do Aluno'),
      ),
      GoRoute(
        path: '/professor',
        builder: (context, state) => const PlaceholderPage(title: 'Área do Professor'),
      ),
      GoRoute(
        path: '/professor/turma/:turmaId',
        builder: (context, state) {
          final turmaId = state.pathParameters['turmaId'] ?? '';
          return PlaceholderPage(title: 'Turma: $turmaId');
        },
      ),
      GoRoute(
        path: '/professor/turma/:turmaId/chat',
        builder: (context, state) {
          final turmaId = int.tryParse(state.pathParameters['turmaId'] ?? '0') ?? 0;
          return ChatPage(turmaId: turmaId);
        },
      ),
      GoRoute(
        path: '/coordenador',
        builder: (context, state) => const PlaceholderPage(title: 'Área do Coordenador'),
      ),
    ],
  );
});
