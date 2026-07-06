import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../data/models/turma_model.dart';
import '../providers/turmas_provider.dart';
import '../../data/repositories/coordenador_repository_impl.dart';
import '../widgets/responsive_entity_view.dart';

class TurmasPage extends ConsumerWidget {
  const TurmasPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final turmasAsync = ref.watch(turmasProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Turmas'),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () => ref.invalidate(turmasProvider),
          ),
        ],
      ),
      body: turmasAsync.when(
        data: (turmas) {
          return ResponsiveEntityView<TurmaModel>(
            data: turmas,
            title: 'Lista de Turmas',
            columns: const ['ID', 'Descrição', 'Disciplina', 'Professor', 'Período', 'Status'],
            buildRowCells: (turma) => [
              DataCell(Text(turma.id?.toString() ?? '-')),
              DataCell(Text(turma.descricao ?? '-')),
              DataCell(Text(turma.nomeDisciplina ?? '-')),
              DataCell(Text(turma.nomeProfessor ?? '-')),
              DataCell(Text(turma.periodoLetivoDescricao ?? '-')),
              DataCell(
                Chip(
                  label: Text(turma.status == 'A' ? 'Ativo' : 'Inativo'),
                  backgroundColor: turma.status == 'A'
                      ? Colors.green.withOpacity(0.1)
                      : Colors.red.withOpacity(0.1),
                ),
              ),
            ],
            getTitle: (turma) => turma.descricao ?? 'Turma sem nome',
            getSubtitle: (turma) => '${turma.nomeDisciplina} - ${turma.nomeProfessor}',
            buildChildren: (turma) => [
              Text('ID: ${turma.id}'),
              Text('Período: ${turma.periodoLetivoDescricao ?? '-'}'),
              Text('Status: ${turma.status == 'A' ? 'Ativo' : 'Inativo'}'),
            ],
            onDelete: (turma) async {
              if (turma.id != null) {
                await ref.read(coordenadorRepositoryProvider).deleteTurma(turma.id!);
                ref.invalidate(turmasProvider);
                if (context.mounted) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Turma excluída com sucesso!')),
                  );
                }
              }
            },
          );
        },
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (err, stack) => Center(child: Text('Erro ao carregar turmas: $err')),
      ),
    );
  }
}
