import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../auth/presentation/providers/auth_provider.dart';
import '../../data/nota_repository.dart';

class NotaFormNotifier extends StateNotifier<AsyncValue<void>> {
  final Ref _ref;
  NotaFormNotifier(this._ref) : super(const AsyncValue.data(null));

  Future<void> salvarNota({
    required int matriculaTurmaId,
    required String descricao,
    required double valor,
  }) async {
    state = const AsyncValue.loading();
    try {
      final authState = _ref.read(authProvider);
      final userId = authState.userId ?? '';
      
      final repository = _ref.read(notaRepositoryProvider);
      await repository.salvarNota({
        'matriculaTurmaId': matriculaTurmaId,
        'descricao': descricao,
        'valor': valor,
      }, userId);
      
      state = const AsyncValue.data(null);
    } catch (e, st) {
      state = AsyncValue.error(e, st);
    }
  }
}

final notaFormProvider = StateNotifierProvider<NotaFormNotifier, AsyncValue<void>>((ref) {
  return NotaFormNotifier(ref);
});
