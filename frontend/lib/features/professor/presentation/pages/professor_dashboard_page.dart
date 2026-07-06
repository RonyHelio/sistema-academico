import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../providers/turmas_professor_provider.dart';
import '../widgets/turma_card.dart';

class ProfessorDashboardPage extends ConsumerStatefulWidget {
  const ProfessorDashboardPage({Key? key}) : super(key: key);

  @override
  ConsumerState<ProfessorDashboardPage> createState() => _ProfessorDashboardPageState();
}

class _ProfessorDashboardPageState extends ConsumerState<ProfessorDashboardPage> {
  int _selectedIndex = 0;

  @override
  Widget build(BuildContext context) {
    final isDesktop = MediaQuery.of(context).size.width >= 800;
    
    Widget content = const _TurmasListView();
    if (_selectedIndex == 1) {
      content = const Center(child: Text('Perfil em breve...'));
    }

    if (isDesktop) {
      return Scaffold(
        body: Row(
          children: [
            NavigationRail(
              selectedIndex: _selectedIndex,
              onDestinationSelected: (int index) {
                setState(() => _selectedIndex = index);
              },
              labelType: NavigationRailLabelType.all,
              destinations: const [
                NavigationRailDestination(
                  icon: Icon(Icons.class_outlined),
                  selectedIcon: Icon(Icons.class_),
                  label: Text('Minhas Turmas'),
                ),
                NavigationRailDestination(
                  icon: Icon(Icons.person_outline),
                  selectedIcon: Icon(Icons.person),
                  label: Text('Perfil'),
                ),
              ],
            ),
            const VerticalDivider(thickness: 1, width: 1),
            Expanded(child: content),
          ],
        ),
      );
    }

    return Scaffold(
      appBar: AppBar(
        title: Text(_selectedIndex == 0 ? 'Minhas Turmas' : 'Perfil'),
        centerTitle: true,
      ),
      body: content,
      bottomNavigationBar: NavigationBar(
        selectedIndex: _selectedIndex,
        onDestinationSelected: (int index) {
          setState(() => _selectedIndex = index);
        },
        destinations: const [
          NavigationDestination(
            icon: Icon(Icons.class_outlined),
            selectedIcon: Icon(Icons.class_),
            label: 'Turmas',
          ),
          NavigationDestination(
            icon: Icon(Icons.person_outline),
            selectedIcon: Icon(Icons.person),
            label: 'Perfil',
          ),
        ],
      ),
    );
  }
}

class _TurmasListView extends ConsumerWidget {
  const _TurmasListView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final turmasAsyncValue = ref.watch(turmasProfessorProvider);

    return turmasAsyncValue.when(
      data: (turmas) {
        if (turmas.isEmpty) {
          return const Center(
            child: Text('Nenhuma turma encontrada.'),
          );
        }
        return ListView.builder(
          itemCount: turmas.length,
          itemBuilder: (context, index) {
            return TurmaCard(turma: turmas[index]);
          },
        );
      },
      loading: () => const Center(child: CircularProgressIndicator()),
      error: (error, stack) => Center(
        child: Text('Erro ao carregar turmas: $error'),
      ),
    );
  }
}
