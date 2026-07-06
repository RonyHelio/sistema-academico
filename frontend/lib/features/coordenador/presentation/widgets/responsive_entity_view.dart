import 'package:flutter/material.dart';
import 'entity_data_table.dart';
import 'entity_expansion_list.dart';

class ResponsiveEntityView<T> extends StatelessWidget {
  final List<T> data;
  
  // For Desktop Table
  final List<String> columns;
  final List<DataCell> Function(T item) buildRowCells;
  final String title;

  // For Mobile List
  final String Function(T item) getTitle;
  final String Function(T item)? getSubtitle;
  final List<Widget> Function(T item) buildChildren;

  // Shared
  final Future<void> Function(T item)? onDelete;

  const ResponsiveEntityView({
    super.key,
    required this.data,
    required this.columns,
    required this.buildRowCells,
    required this.getTitle,
    this.getSubtitle,
    required this.buildChildren,
    this.onDelete,
    this.title = '',
  });

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        if (constraints.maxWidth >= 800) {
          // Desktop mode
          return Padding(
            padding: const EdgeInsets.all(16.0),
            child: EntityDataTable<T>(
              data: data,
              columns: columns,
              buildRowCells: buildRowCells,
              onDelete: onDelete,
              title: title,
            ),
          );
        } else {
          // Mobile mode
          return EntityExpansionList<T>(
            data: data,
            title: getTitle,
            subtitle: getSubtitle,
            children: buildChildren,
            onDelete: onDelete,
          );
        }
      },
    );
  }
}
