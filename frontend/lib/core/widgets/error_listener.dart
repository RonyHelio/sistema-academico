import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../errors/error_notifier.dart';
import 'app_snackbar.dart';

class ErrorListener extends ConsumerWidget {
  final Widget child;

  const ErrorListener({super.key, required this.child});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    ref.listen(errorNotifierProvider, (previous, current) {
      if (current != null) {
        AppSnackbar.showError(context, current.message);
        // Limpa o erro após ser exibido para permitir que ele apareça novamente, se necessário
        Future.microtask(() => ref.read(errorNotifierProvider.notifier).clear());
      }
    });

    return child;
  }
}
