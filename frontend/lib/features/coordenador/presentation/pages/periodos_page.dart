import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../data/models/periodo_letivo_model.dart';
import '../providers/periodos_provider.dart';
import '../../data/repositories/coordenador_repository_impl.dart';
import '../widgets/responsive_entity_view.dart';

class PeriodosPage extends ConsumerWidget {
  const PeriodosPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final periodosAsync = ref.watch(periodosProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Períodos Letivos'),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () => ref.invalidate(periodosProvider),
          ),
        ],
      ),
      body: periodosAsync.when(
        data: (periodos) {
          return ResponsiveEntityView<PeriodoLetivoModel>(
            data: periodos,
            title: 'Lista de Períodos Letivos',
            columns: const ['ID', 'Ano', 'Semestre', 'Descrição', 'Status'],
            buildRowCells: (periodo) => [
              DataCell(Text(periodo.id?.toString() ?? '-')),
              DataCell(Text(periodo.ano?.toString() ?? '-')),
              DataCell(Text(periodo.semestre?.toString() ?? '-')),
              DataCell(Text(periodo.descricao ?? '-')),
              DataCell(
                Chip(
                  label: Text(periodo.status == 'A' ? 'Ativo' : 'Inativo'),
                  backgroundColor: periodo.status == 'A'
                      ? Colors.green.withOpacity(0.1)
                      : Colors.red.withOpacity(0.1),
                ),
              ),
            ],
            getTitle: (periodo) => periodo.descricao ?? '${periodo.ano}.${periodo.semestre}',
            getSubtitle: (periodo) => 'Ano: ${periodo.ano} - Semestre: ${periodo.semestre}',
            buildChildren: (periodo) => [
              Text('ID: ${periodo.id}'),
              Text('Status: ${periodo.status == 'A' ? 'Ativo' : 'Inativo'}'),
            ],
            onDelete: (periodo) async {
              if (periodo.id != null) {
                await ref.read(coordenadorRepositoryProvider).deletePeriodo(periodo.id!);
                ref.invalidate(periodosProvider);
                if (context.mounted) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Período letivo excluído com sucesso!')),
                  );
                }
              }
            },
          );
        },
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (err, stack) => Center(child: Text('Erro ao carregar períodos: $err')),
      ),
    );
  }
}
