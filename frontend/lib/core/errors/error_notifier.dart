import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'app_error.dart';

class ErrorNotifier extends StateNotifier<AppError?> {
  ErrorNotifier() : super(null);

  void notifyError(AppError error) {
    state = error;
  }

  void clear() {
    state = null;
  }
}

final errorNotifierProvider = StateNotifierProvider<ErrorNotifier, AppError?>((ref) {
  return ErrorNotifier();
});
