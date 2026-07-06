import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../data/models/curso_model.dart';
import '../providers/cursos_provider.dart';
import '../../data/repositories/coordenador_repository_impl.dart';
import '../widgets/responsive_entity_view.dart';

class CursosPage extends ConsumerWidget {
  const CursosPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final cursosAsync = ref.watch(cursosProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Cursos'),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () => ref.invalidate(cursosProvider),
          ),
        ],
      ),
      body: cursosAsync.when(
        data: (cursos) {
          return ResponsiveEntityView<CursoModel>(
            data: cursos,
            title: 'Lista de Cursos',
            columns: const ['ID', 'Nome', 'Cód SUAP', 'Status'],
            buildRowCells: (curso) => [
              DataCell(Text(curso.id?.toString() ?? '-')),
              DataCell(Text(curso.nome ?? '-')),
              DataCell(Text(curso.codigoSuap ?? '-')),
              DataCell(
                Chip(
                  label: Text(curso.status == 'A' ? 'Ativo' : 'Inativo'),
                  backgroundColor: curso.status == 'A'
                      ? Colors.green.withOpacity(0.1)
                      : Colors.red.withOpacity(0.1),
                ),
              ),
            ],
            getTitle: (curso) => curso.nome ?? 'Sem nome',
            getSubtitle: (curso) => 'Cód: ${curso.codigoSuap ?? '-'}',
            buildChildren: (curso) => [
              Text('ID: ${curso.id}'),
              Text('Status: ${curso.status == 'A' ? 'Ativo' : 'Inativo'}'),
            ],
            onDelete: (curso) async {
              if (curso.id != null) {
                await ref.read(coordenadorRepositoryProvider).deleteCurso(curso.id!);
                ref.invalidate(cursosProvider);
                if (context.mounted) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Curso excluído com sucesso!')),
                  );
                }
              }
            },
          );
        },
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (err, stack) => Center(child: Text('Erro ao carregar cursos: $err')),
      ),
    );
  }
}
