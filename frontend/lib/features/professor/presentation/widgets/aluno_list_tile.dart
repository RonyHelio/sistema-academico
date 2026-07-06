import 'package:flutter/material.dart';
import '../../../../data/models/matricula_turma_model.dart';
import 'nota_form_sheet.dart';
import 'falta_form_sheet.dart';

class AlunoListTile extends StatelessWidget {
  final MatriculaTurmaModel matricula;

  const AlunoListTile({Key? key, required this.matricula}) : super(key: key);

  void _showNotaForm(BuildContext context) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
      ),
      builder: (context) => Padding(
        padding: EdgeInsets.only(bottom: MediaQuery.of(context).viewInsets.bottom),
        child: NotaFormSheet(matriculaTurmaId: matricula.id!),
      ),
    );
  }

  void _showFaltaForm(BuildContext context) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
      ),
      builder: (context) => Padding(
        padding: EdgeInsets.only(bottom: MediaQuery.of(context).viewInsets.bottom),
        child: FaltaFormSheet(matriculaTurmaId: matricula.id!),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Card(
      elevation: 0,
      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 4),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12),
        side: BorderSide(color: theme.colorScheme.outlineVariant),
      ),
      child: ListTile(
        contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
        leading: CircleAvatar(
          backgroundColor: theme.colorScheme.primaryContainer,
          foregroundColor: theme.colorScheme.onPrimaryContainer,
          child: Text(
            matricula.nomeAluno?.isNotEmpty == true ? matricula.nomeAluno![0].toUpperCase() : 'A',
            style: const TextStyle(fontWeight: FontWeight.bold),
          ),
        ),
        title: Text(
          matricula.nomeAluno ?? 'Aluno Desconhecido',
          style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.w600),
        ),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            IconButton(
              icon: Icon(Icons.grading, color: theme.colorScheme.primary),
              tooltip: 'Lançar Nota',
              onPressed: () => _showNotaForm(context),
            ),
            IconButton(
              icon: Icon(Icons.event_busy, color: theme.colorScheme.error),
              tooltip: 'Lançar Falta',
              onPressed: () => _showFaltaForm(context),
            ),
          ],
        ),
      ),
    );
  }
}
