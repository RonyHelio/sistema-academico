import 'package:flutter/material.dart';

class SituacaoBadge extends StatelessWidget {
  final String situacao;

  const SituacaoBadge({super.key, required this.situacao});

  @override
  Widget build(BuildContext context) {
    Color backgroundColor;
    Color textColor = Colors.white;

    switch (situacao.toUpperCase()) {
      case 'APROVADO':
        backgroundColor = Colors.green.shade600;
        break;
      case 'REPROVADO':
        backgroundColor = Colors.red.shade600;
        break;
      case 'CURSANDO':
        backgroundColor = Colors.blue.shade600;
        break;
      default:
        backgroundColor = Colors.grey.shade600;
    }

    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
      decoration: BoxDecoration(
        color: backgroundColor,
        borderRadius: BorderRadius.circular(16),
        boxShadow: [
          BoxShadow(
            color: backgroundColor.withOpacity(0.4),
            blurRadius: 4,
            offset: const Offset(0, 2),
          ),
        ],
      ),
      child: Text(
        situacao.toUpperCase(),
        style: TextStyle(
          color: textColor,
          fontSize: 12,
          fontWeight: FontWeight.bold,
          letterSpacing: 0.8,
        ),
      ),
    );
  }
}
