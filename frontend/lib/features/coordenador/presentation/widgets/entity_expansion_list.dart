import 'package:flutter/material.dart';
import 'confirm_delete_dialog.dart';

class EntityExpansionList<T> extends StatelessWidget {
  final List<T> data;
  final String Function(T item) title;
  final String Function(T item)? subtitle;
  final List<Widget> Function(T item) children;
  final Future<void> Function(T item)? onDelete;

  const EntityExpansionList({
    super.key,
    required this.data,
    required this.title,
    this.subtitle,
    required this.children,
    this.onDelete,
  });

  @override
  Widget build(BuildContext context) {
    if (data.isEmpty) {
      return Center(
        child: Text(
          'Nenhum registro encontrado.',
          style: Theme.of(context).textTheme.titleMedium,
        ),
      );
    }

    return ListView.builder(
      itemCount: data.length,
      padding: const EdgeInsets.all(8),
      itemBuilder: (context, index) {
        final item = data[index];
        return Card(
          elevation: 2,
          margin: const EdgeInsets.symmetric(vertical: 4, horizontal: 8),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          child: ExpansionTile(
            title: Text(
              title(item),
              style: const TextStyle(fontWeight: FontWeight.bold),
            ),
            subtitle: subtitle != null ? Text(subtitle!(item)) : null,
            childrenPadding: const EdgeInsets.all(16),
            expandedCrossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              ...children(item),
              if (onDelete != null) ...[
                const SizedBox(height: 16),
                Align(
                  alignment: Alignment.centerRight,
                  child: FilledButton.icon(
                    style: FilledButton.styleFrom(
                      backgroundColor: Theme.of(context).colorScheme.error,
                    ),
                    icon: const Icon(Icons.delete_outline),
                    label: const Text('Excluir'),
                    onPressed: () async {
                      final confirm = await showDialog<bool>(
                        context: context,
                        builder: (ctx) => const ConfirmDeleteDialog(
                          title: 'Confirmar Exclusão',
                          content: 'Tem certeza que deseja excluir este registro?',
                        ),
                      );
                      if (confirm == true) {
                        await onDelete!(item);
                      }
                    },
                  ),
                ),
              ],
            ],
          ),
        );
      },
    );
  }
}
