import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../../data/models/usuario_model.dart';
import '../providers/usuarios_provider.dart';
import '../../data/repositories/coordenador_repository_impl.dart';
import '../widgets/responsive_entity_view.dart';
import 'package:intl/intl.dart';

class UsuariosPage extends ConsumerWidget {
  const UsuariosPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final usuariosAsync = ref.watch(usuariosProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Usuários'),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () => ref.invalidate(usuariosProvider),
          ),
        ],
      ),
      body: usuariosAsync.when(
        data: (usuarios) {
          return ResponsiveEntityView<UsuarioModel>(
            data: usuarios,
            title: 'Lista de Usuários',
            columns: const ['ID', 'Nome', 'E-mail', 'Login', 'Perfil', 'Cadastro', 'Status'],
            buildRowCells: (usuario) => [
              DataCell(Text(usuario.id?.toString() ?? '-')),
              DataCell(Text(usuario.nome ?? '-')),
              DataCell(Text(usuario.email ?? '-')),
              DataCell(Text(usuario.login ?? '-')),
              DataCell(Text(usuario.perfil ?? '-')),
              DataCell(Text(usuario.cadastro != null ? DateFormat('dd/MM/yyyy HH:mm').format(usuario.cadastro!) : '-')),
              DataCell(
                Chip(
                  label: Text(usuario.status == 'A' ? 'Ativo' : 'Inativo'),
                  backgroundColor: usuario.status == 'A'
                      ? Colors.green.withOpacity(0.1)
                      : Colors.red.withOpacity(0.1),
                ),
              ),
            ],
            getTitle: (usuario) => usuario.nome ?? 'Usuário sem nome',
            getSubtitle: (usuario) => 'Login: ${usuario.login ?? '-'} | Perfil: ${usuario.perfil ?? '-'}',
            buildChildren: (usuario) => [
              Text('ID: ${usuario.id}'),
              Text('E-mail: ${usuario.email ?? '-'}'),
              Text('Cadastro: ${usuario.cadastro != null ? DateFormat('dd/MM/yyyy HH:mm').format(usuario.cadastro!) : '-'}'),
              Text('Status: ${usuario.status == 'A' ? 'Ativo' : 'Inativo'}'),
            ],
            onDelete: (usuario) async {
              if (usuario.id != null) {
                await ref.read(coordenadorRepositoryProvider).deleteUsuario(usuario.id!);
                ref.invalidate(usuariosProvider);
                if (context.mounted) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Usuário excluído com sucesso!')),
                  );
                }
              }
            },
          );
        },
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (err, stack) => Center(child: Text('Erro ao carregar usuários: $err')),
      ),
    );
  }
}
