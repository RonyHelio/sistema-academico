import 'package:flutter/material.dart';
import 'dart:ui';
import '../theme/app_colors.dart';

class ResponsiveScaffold extends StatelessWidget {
  final Widget child;
  final Widget? drawer;
  final Widget? sideMenu; // Para telas desktop e tablet
  final PreferredSizeWidget? appBar;
  final bool showBottomNav;
  final List<NavigationDestination>? bottomNavItems;
  final int currentBottomNavIndex;
  final ValueChanged<int>? onBottomNavTap;

  const ResponsiveScaffold({
    super.key,
    required this.child,
    this.drawer,
    this.sideMenu,
    this.appBar,
    this.showBottomNav = false,
    this.bottomNavItems,
    this.currentBottomNavIndex = 0,
    this.onBottomNavTap,
  });

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        final isDesktop = constraints.maxWidth >= 1024;
        final isTablet = constraints.maxWidth >= 600 && constraints.maxWidth < 1024;

        return Scaffold(
          extendBodyBehindAppBar: true,
          appBar: appBar,
          drawer: (isDesktop || isTablet) ? null : drawer,
          body: Stack(
            children: [
              // Fundo Premium com Gradiente Suave
              Container(
                decoration: const BoxDecoration(
                  gradient: LinearGradient(
                    begin: Alignment.topLeft,
                    end: Alignment.bottomRight,
                    colors: [
                      AppColors.background,
                      Color(0xFFE5E7EB), // Mesclagem suave de cinza claro
                    ],
                  ),
                ),
              ),
              Row(
                children: [
                  if ((isDesktop || isTablet) && sideMenu != null)
                    // Sidebar com efeito Glassmorphism
                    ClipRRect(
                      child: BackdropFilter(
                        filter: ImageFilter.blur(sigmaX: 10, sigmaY: 10),
                        child: AnimatedContainer(
                          duration: const Duration(milliseconds: 300),
                          width: isDesktop ? 280 : 80,
                          decoration: BoxDecoration(
                            color: AppColors.surfaceGlass,
                            border: const Border(
                              right: BorderSide(
                                color: Colors.white,
                                width: 1.5,
                              ),
                            ),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.black.withOpacity(0.05),
                                blurRadius: 20,
                              )
                            ],
                          ),
                          child: sideMenu,
                        ),
                      ),
                    ),
                  Expanded(
                    child: SafeArea(
                      bottom: !showBottomNav,
                      child: AnimatedSwitcher(
                        duration: const Duration(milliseconds: 300),
                        switchInCurve: Curves.easeIn,
                        switchOutCurve: Curves.easeOut,
                        child: child,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ),
          bottomNavigationBar: (!isDesktop && !isTablet && showBottomNav && bottomNavItems != null)
              ? ClipRRect(
                  child: BackdropFilter(
                    filter: ImageFilter.blur(sigmaX: 10, sigmaY: 10),
                    child: NavigationBar(
                      backgroundColor: AppColors.surfaceGlass,
                      elevation: 0,
                      selectedIndex: currentBottomNavIndex,
                      onDestinationSelected: onBottomNavTap,
                      destinations: bottomNavItems!,
                      indicatorColor: AppColors.primary.withOpacity(0.2),
                    ),
                  ),
                )
              : null,
        );
      },
    );
  }
}
