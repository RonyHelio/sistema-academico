import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../../data/models/turma_model.dart';
import '../providers/alunos_turma_provider.dart';
import '../widgets/aluno_list_tile.dart';

class AlunosTurmaPage extends ConsumerWidget {
  final TurmaModel turma;

  const AlunosTurmaPage({Key? key, required this.turma}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final theme = Theme.of(context);
    final alunosAsyncValue = ref.watch(alunosTurmaProvider(turma.id!));

    return Scaffold(
      appBar: AppBar(
        title: Hero(
          tag: 'turma-${turma.id}',
          child: Material(
            color: Colors.transparent,
            child: Text(
              turma.nomeDisciplina ?? 'Turma',
              style: theme.textTheme.titleLarge,
            ),
          ),
        ),
      ),
      body: alunosAsyncValue.when(
        data: (alunos) {
          if (alunos.isEmpty) {
            return const Center(child: Text('Nenhum aluno matriculado.'));
          }
          return ListView.builder(
            itemCount: alunos.length,
            itemBuilder: (context, index) {
              return AlunoListTile(matricula: alunos[index]);
            },
          );
        },
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (error, stack) => Center(
          child: Text('Erro ao carregar alunos: $error'),
        ),
      ),
    );
  }
}
