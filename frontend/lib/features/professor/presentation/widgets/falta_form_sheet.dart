import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../providers/falta_form_provider.dart';
import 'package:intl/intl.dart';

class FaltaFormSheet extends ConsumerStatefulWidget {
  final int matriculaTurmaId;

  const FaltaFormSheet({Key? key, required this.matriculaTurmaId}) : super(key: key);

  @override
  ConsumerState<FaltaFormSheet> createState() => _FaltaFormSheetState();
}

class _FaltaFormSheetState extends ConsumerState<FaltaFormSheet> {
  final _formKey = GlobalKey<FormState>();
  final _quantidadeController = TextEditingController(text: '1');
  final _observacaoController = TextEditingController();
  DateTime _selectedDate = DateTime.now();

  @override
  void dispose() {
    _quantidadeController.dispose();
    _observacaoController.dispose();
    super.dispose();
  }

  Future<void> _pickDate() async {
    final picked = await showDatePicker(
      context: context,
      initialDate: _selectedDate,
      firstDate: DateTime(2000),
      lastDate: DateTime(2100),
    );
    if (picked != null) {
      setState(() {
        _selectedDate = picked;
      });
    }
  }

  void _submit() async {
    if (_formKey.currentState!.validate()) {
      await ref.read(faltaFormProvider.notifier).salvarFalta(
        matriculaTurmaId: widget.matriculaTurmaId,
        data: _selectedDate,
        quantidade: int.parse(_quantidadeController.text),
        observacao: _observacaoController.text.trim().isEmpty ? null : _observacaoController.text.trim(),
      );

      if (mounted) {
        final state = ref.read(faltaFormProvider);
        if (!state.hasError) {
          Navigator.of(context).pop();
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(content: Text('Falta salva com sucesso!')),
          );
        } else {
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(content: Text('Erro ao salvar: ${state.error}')),
          );
        }
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isLoading = ref.watch(faltaFormProvider).isLoading;
    final formattedDate = DateFormat('dd/MM/yyyy').format(_selectedDate);

    return Container(
      padding: const EdgeInsets.all(24),
      decoration: BoxDecoration(
        color: theme.colorScheme.surface,
        borderRadius: const BorderRadius.vertical(top: Radius.circular(24)),
      ),
      child: Form(
        key: _formKey,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Lançar Falta',
              style: theme.textTheme.headlineSmall?.copyWith(
                fontWeight: FontWeight.bold,
                color: theme.colorScheme.error,
              ),
            ),
            const SizedBox(height: 16),
            InkWell(
              onTap: _pickDate,
              borderRadius: BorderRadius.circular(4),
              child: InputDecorator(
                decoration: const InputDecoration(
                  labelText: 'Data da Falta',
                  border: OutlineInputBorder(),
                  prefixIcon: Icon(Icons.calendar_today),
                ),
                child: Text(formattedDate, style: theme.textTheme.bodyLarge),
              ),
            ),
            const SizedBox(height: 16),
            TextFormField(
              controller: _quantidadeController,
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                labelText: 'Quantidade de Faltas',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.exposure_minus_1),
              ),
              validator: (value) {
                if (value == null || value.trim().isEmpty) return 'Informe a quantidade';
                final num = int.tryParse(value);
                if (num == null) return 'Valor inválido';
                if (num < 1) return 'No mínimo 1 falta';
                return null;
              },
            ),
            const SizedBox(height: 16),
            TextFormField(
              controller: _observacaoController,
              decoration: const InputDecoration(
                labelText: 'Observação (Opcional)',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.notes),
              ),
            ),
            const SizedBox(height: 24),
            SizedBox(
              width: double.infinity,
              height: 48,
              child: FilledButton(
                style: FilledButton.styleFrom(backgroundColor: theme.colorScheme.error),
                onPressed: isLoading ? null : _submit,
                child: isLoading
                    ? const SizedBox(
                        height: 24,
                        width: 24,
                        child: CircularProgressIndicator(strokeWidth: 2, color: Colors.white),
                      )
                    : const Text('Salvar Falta', style: TextStyle(fontSize: 16)),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
