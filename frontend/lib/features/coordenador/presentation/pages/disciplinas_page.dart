import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../data/models/disciplina_model.dart';
import '../providers/disciplinas_provider.dart';
import '../../data/repositories/coordenador_repository_impl.dart';
import '../widgets/responsive_entity_view.dart';

class DisciplinasPage extends ConsumerWidget {
  const DisciplinasPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final disciplinasAsync = ref.watch(disciplinasProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Disciplinas'),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () => ref.invalidate(disciplinasProvider),
          ),
        ],
      ),
      body: disciplinasAsync.when(
        data: (disciplinas) {
          return ResponsiveEntityView<DisciplinaModel>(
            data: disciplinas,
            title: 'Lista de Disciplinas',
            columns: const ['ID', 'Nome', 'Código', 'CH', 'Status'],
            buildRowCells: (disciplina) => [
              DataCell(Text(disciplina.id?.toString() ?? '-')),
              DataCell(Text(disciplina.nome ?? '-')),
              DataCell(Text(disciplina.codigo ?? '-')),
              DataCell(Text(disciplina.cargaHoraria?.toString() ?? '-')),
              DataCell(
                Chip(
                  label: Text(disciplina.status == 'A' ? 'Ativo' : 'Inativo'),
                  backgroundColor: disciplina.status == 'A'
                      ? Colors.green.withOpacity(0.1)
                      : Colors.red.withOpacity(0.1),
                ),
              ),
            ],
            getTitle: (disciplina) => disciplina.nome ?? 'Sem nome',
            getSubtitle: (disciplina) => 'Cód: ${disciplina.codigo ?? '-'} | CH: ${disciplina.cargaHoraria ?? 0}h',
            buildChildren: (disciplina) => [
              Text('ID: ${disciplina.id}'),
              Text('Cód SUAP: ${disciplina.codigoSuap ?? '-'}'),
              Text('Status: ${disciplina.status == 'A' ? 'Ativo' : 'Inativo'}'),
            ],
            onDelete: (disciplina) async {
              if (disciplina.id != null) {
                await ref.read(coordenadorRepositoryProvider).deleteDisciplina(disciplina.id!);
                ref.invalidate(disciplinasProvider);
                if (context.mounted) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Disciplina excluída com sucesso!')),
                  );
                }
              }
            },
          );
        },
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (err, stack) => Center(child: Text('Erro ao carregar disciplinas: $err')),
      ),
    );
  }
}
