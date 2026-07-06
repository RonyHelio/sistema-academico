import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../auth/presentation/providers/auth_provider.dart';
import '../../data/falta_repository.dart';

class FaltaFormNotifier extends StateNotifier<AsyncValue<void>> {
  final Ref _ref;
  FaltaFormNotifier(this._ref) : super(const AsyncValue.data(null));

  Future<void> salvarFalta({
    required int matriculaTurmaId,
    required DateTime data,
    required int quantidade,
    String? observacao,
  }) async {
    state = const AsyncValue.loading();
    try {
      final authState = _ref.read(authProvider);
      final userId = authState.userId ?? '';
      
      final repository = _ref.read(faltaRepositoryProvider);
      await repository.salvarFalta({
        'matriculaTurmaId': matriculaTurmaId,
        'data': data.toIso8601String().split('T')[0],
        'quantidade': quantidade,
        'observacao': observacao,
      }, userId);
      
      state = const AsyncValue.data(null);
    } catch (e, st) {
      state = AsyncValue.error(e, st);
    }
  }
}

final faltaFormProvider = StateNotifierProvider<FaltaFormNotifier, AsyncValue<void>>((ref) {
  return FaltaFormNotifier(ref);
});
