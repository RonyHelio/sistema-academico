import 'package:flutter/material.dart';
import '../../data/models/situacao_aluno_turma_model.dart';
import 'situacao_badge.dart';

class SituacaoCard extends StatelessWidget {
  final SituacaoAlunoTurmaModel model;

  const SituacaoCard({super.key, required this.model});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Card(
      elevation: 4,
      shadowColor: Colors.black26,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
      child: Container(
        padding: const EdgeInsets.all(20),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          gradient: LinearGradient(
            colors: [
              theme.colorScheme.surface,
              theme.colorScheme.surfaceContainerHighest.withOpacity(0.4),
            ],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        model.nomeDisciplina,
                        style: theme.textTheme.titleLarge?.copyWith(
                          fontWeight: FontWeight.w800,
                          color: theme.colorScheme.primary,
                          letterSpacing: -0.5,
                        ),
                        maxLines: 2,
                        overflow: TextOverflow.ellipsis,
                      ),
                      const SizedBox(height: 8),
                      Text(
                        '${model.descricaoTurma} • ${model.periodoLetivo}',
                        style: theme.textTheme.bodyMedium?.copyWith(
                          color: theme.colorScheme.onSurfaceVariant,
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                    ],
                  ),
                ),
                const SizedBox(width: 12),
                SituacaoBadge(situacao: model.situacao),
              ],
            ),
            const Spacer(),
            const Divider(height: 32),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                _buildStatInfo(
                  context,
                  icon: Icons.grade_rounded,
                  label: 'Média',
                  value: model.mediaNotas.toStringAsFixed(1),
                  color: Colors.amber.shade700,
                ),
                Container(
                  width: 1.5,
                  height: 40,
                  color: theme.colorScheme.outlineVariant,
                ),
                _buildStatInfo(
                  context,
                  icon: Icons.event_busy_rounded,
                  label: 'Faltas',
                  value: model.totalFaltas.toString(),
                  color: Colors.red.shade500,
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildStatInfo(BuildContext context, {required IconData icon, required String label, required String value, required Color color}) {
    final theme = Theme.of(context);
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            Icon(icon, size: 18, color: color),
            const SizedBox(width: 6),
            Text(
              label,
              style: theme.textTheme.bodyMedium?.copyWith(
                color: theme.colorScheme.onSurfaceVariant,
                fontWeight: FontWeight.w600,
              ),
            ),
          ],
        ),
        const SizedBox(height: 6),
        Text(
          value,
          style: theme.textTheme.headlineSmall?.copyWith(
            fontWeight: FontWeight.w900,
            color: theme.colorScheme.onSurface,
            letterSpacing: -0.5,
          ),
        ),
      ],
    );
  }
}
