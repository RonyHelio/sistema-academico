import 'package:flutter/material.dart';

import 'cursos_page.dart';
import 'disciplinas_page.dart';
import 'periodos_page.dart';
import 'turmas_page.dart';
import 'alunos_coord_page.dart';
import 'usuarios_page.dart';

class CoordenadorDashboardPage extends StatefulWidget {
  const CoordenadorDashboardPage({super.key});

  @override
  State<CoordenadorDashboardPage> createState() => _CoordenadorDashboardPageState();
}

class _CoordenadorDashboardPageState extends State<CoordenadorDashboardPage> {
  int _selectedIndex = 0;

  final List<Widget> _pages = const [
    CursosPage(),
    DisciplinasPage(),
    PeriodosPage(),
    TurmasPage(),
    AlunosCoordPage(),
    UsuariosPage(),
  ];

  final List<NavigationDestination> _destinations = const [
    NavigationDestination(icon: Icon(Icons.school_outlined), selectedIcon: Icon(Icons.school), label: 'Cursos'),
    NavigationDestination(icon: Icon(Icons.menu_book_outlined), selectedIcon: Icon(Icons.menu_book), label: 'Disciplinas'),
    NavigationDestination(icon: Icon(Icons.calendar_month_outlined), selectedIcon: Icon(Icons.calendar_month), label: 'Períodos'),
    NavigationDestination(icon: Icon(Icons.class_outlined), selectedIcon: Icon(Icons.class_), label: 'Turmas'),
    NavigationDestination(icon: Icon(Icons.people_outline), selectedIcon: Icon(Icons.people), label: 'Alunos'),
    NavigationDestination(icon: Icon(Icons.manage_accounts_outlined), selectedIcon: Icon(Icons.manage_accounts), label: 'Usuários'),
  ];

  final List<NavigationRailDestination> _railDestinations = const [
    NavigationRailDestination(icon: Icon(Icons.school_outlined), selectedIcon: Icon(Icons.school), label: Text('Cursos')),
    NavigationRailDestination(icon: Icon(Icons.menu_book_outlined), selectedIcon: Icon(Icons.menu_book), label: Text('Disciplinas')),
    NavigationRailDestination(icon: Icon(Icons.calendar_month_outlined), selectedIcon: Icon(Icons.calendar_month), label: Text('Períodos')),
    NavigationRailDestination(icon: Icon(Icons.class_outlined), selectedIcon: Icon(Icons.class_), label: Text('Turmas')),
    NavigationRailDestination(icon: Icon(Icons.people_outline), selectedIcon: Icon(Icons.people), label: Text('Alunos')),
    NavigationRailDestination(icon: Icon(Icons.manage_accounts_outlined), selectedIcon: Icon(Icons.manage_accounts), label: Text('Usuários')),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: LayoutBuilder(
        builder: (context, constraints) {
          if (constraints.maxWidth >= 800) {
            // Desktop Layout with NavigationRail
            return Row(
              children: [
                NavigationRail(
                  selectedIndex: _selectedIndex,
                  onDestinationSelected: (index) {
                    setState(() {
                      _selectedIndex = index;
                    });
                  },
                  labelType: NavigationRailLabelType.all,
                  destinations: _railDestinations,
                  leading: const Padding(
                    padding: EdgeInsets.symmetric(vertical: 16.0),
                    child: CircleAvatar(
                      child: Icon(Icons.admin_panel_settings),
                    ),
                  ),
                ),
                const VerticalDivider(thickness: 1, width: 1),
                Expanded(
                  child: _pages[_selectedIndex],
                ),
              ],
            );
          } else {
            // Mobile Layout with NavigationBar
            return _pages[_selectedIndex];
          }
        },
      ),
      bottomNavigationBar: MediaQuery.of(context).size.width < 800
          ? NavigationBar(
              selectedIndex: _selectedIndex,
              onDestinationSelected: (index) {
                setState(() {
                  _selectedIndex = index;
                });
              },
              destinations: _destinations,
            )
          : null,
    );
  }
}
