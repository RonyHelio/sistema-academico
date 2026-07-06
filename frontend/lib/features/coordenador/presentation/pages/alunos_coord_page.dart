import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../data/models/aluno_model.dart';
import '../providers/alunos_coord_provider.dart';
import '../../data/repositories/coordenador_repository_impl.dart';
import '../widgets/responsive_entity_view.dart';

class AlunosCoordPage extends ConsumerWidget {
  const AlunosCoordPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final alunosAsync = ref.watch(alunosCoordProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Alunos'),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () => ref.invalidate(alunosCoordProvider),
          ),
        ],
      ),
      body: alunosAsync.when(
        data: (alunos) {
          return ResponsiveEntityView<AlunoModel>(
            data: alunos,
            title: 'Lista de Alunos',
            columns: const ['ID', 'Nome', 'Matrícula', 'E-mail', 'Curso', 'Status'],
            buildRowCells: (aluno) => [
              DataCell(Text(aluno.id?.toString() ?? '-')),
              DataCell(Text(aluno.nomeAluno ?? '-')),
              DataCell(Text(aluno.matricula ?? '-')),
              DataCell(Text(aluno.emailAluno ?? '-')),
              DataCell(Text(aluno.nomeCurso ?? '-')),
              DataCell(
                Chip(
                  label: Text(aluno.status == 'A' ? 'Ativo' : 'Inativo'),
                  backgroundColor: aluno.status == 'A'
                      ? Colors.green.withOpacity(0.1)
                      : Colors.red.withOpacity(0.1),
                ),
              ),
            ],
            getTitle: (aluno) => aluno.nomeAluno ?? 'Aluno sem nome',
            getSubtitle: (aluno) => 'Matrícula: ${aluno.matricula ?? '-'} | Curso: ${aluno.nomeCurso ?? '-'}',
            buildChildren: (aluno) => [
              Text('ID: ${aluno.id}'),
              Text('E-mail: ${aluno.emailAluno ?? '-'}'),
              Text('Status: ${aluno.status == 'A' ? 'Ativo' : 'Inativo'}'),
            ],
            onDelete: (aluno) async {
              if (aluno.id != null) {
                await ref.read(coordenadorRepositoryProvider).deleteAluno(aluno.id!);
                ref.invalidate(alunosCoordProvider);
                if (context.mounted) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Aluno excluído com sucesso!')),
                  );
                }
              }
            },
          );
        },
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (err, stack) => Center(child: Text('Erro ao carregar alunos: $err')),
      ),
    );
  }
}
