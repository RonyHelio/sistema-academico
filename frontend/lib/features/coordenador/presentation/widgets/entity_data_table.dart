import 'package:flutter/material.dart';
import 'confirm_delete_dialog.dart';

class EntityDataTable<T> extends StatelessWidget {
  final List<T> data;
  final List<String> columns;
  final List<DataCell> Function(T item) buildRowCells;
  final Future<void> Function(T item)? onDelete;
  final String title;

  const EntityDataTable({
    super.key,
    required this.data,
    required this.columns,
    required this.buildRowCells,
    this.onDelete,
    this.title = '',
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

    return SingleChildScrollView(
      child: PaginatedDataTable(
        header: Text(title),
        columns: [
          ...columns.map((col) => DataColumn(label: Text(col, style: const TextStyle(fontWeight: FontWeight.bold)))),
          if (onDelete != null) const DataColumn(label: Text('Ações', style: TextStyle(fontWeight: FontWeight.bold))),
        ],
        source: _EntityDataSource<T>(
          context: context,
          data: data,
          buildRowCells: buildRowCells,
          onDelete: onDelete,
        ),
        rowsPerPage: data.length > 10 ? 10 : (data.isEmpty ? 1 : data.length),
        showCheckboxColumn: false,
      ),
    );
  }
}

class _EntityDataSource<T> extends DataTableSource {
  final BuildContext context;
  final List<T> data;
  final List<DataCell> Function(T item) buildRowCells;
  final Future<void> Function(T item)? onDelete;

  _EntityDataSource({
    required this.context,
    required this.data,
    required this.buildRowCells,
    this.onDelete,
  });

  @override
  DataRow? getRow(int index) {
    if (index >= data.length) return null;
    final item = data[index];
    final cells = buildRowCells(item);

    return DataRow(
      cells: [
        ...cells,
        if (onDelete != null)
          DataCell(
            IconButton(
              icon: Icon(Icons.delete_outline, color: Theme.of(context).colorScheme.error),
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
    );
  }

  @override
  bool get isRowCountApproximate => false;

  @override
  int get rowCount => data.length;

  @override
  int get selectedRowCount => 0;
}
