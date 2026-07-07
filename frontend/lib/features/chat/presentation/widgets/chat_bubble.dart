import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../auth/presentation/providers/auth_provider.dart';
import '../../../auth/data/auth_state.dart';
import '../../domain/mensagem_chat.dart';

class ChatBubble extends ConsumerWidget {
  final MensagemChat message;
  final VoidCallback onDelete;

  const ChatBubble({
    super.key,
    required this.message,
    required this.onDelete,
  });

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final authState = ref.watch(authProvider);
    final isMe = authState.userId != null && message.remetenteId.toString() == authState.userId;
    final isCoordenador = authState.perfil == Perfil.admin;
    final canDelete = !message.isInactive && (isMe || isCoordenador);

    Widget bubble = Align(
      alignment: isMe ? Alignment.centerRight : Alignment.centerLeft,
      child: Container(
        margin: const EdgeInsets.symmetric(vertical: 4, horizontal: 8),
        padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 14),
        decoration: BoxDecoration(
          color: message.isInactive
              ? Theme.of(context).disabledColor.withOpacity(0.2)
              : (isMe ? Theme.of(context).colorScheme.primary : Theme.of(context).colorScheme.surfaceVariant),
          borderRadius: BorderRadius.only(
            topLeft: const Radius.circular(16),
            topRight: const Radius.circular(16),
            bottomLeft: isMe ? const Radius.circular(16) : const Radius.circular(0),
            bottomRight: isMe ? const Radius.circular(0) : const Radius.circular(16),
          ),
        ),
        child: Column(
          crossAxisAlignment: isMe ? CrossAxisAlignment.end : CrossAxisAlignment.start,
          children: [
            if (!isMe && !message.isInactive && message.nomeRemetente != null)
              Padding(
                padding: const EdgeInsets.only(bottom: 4),
                child: Text(
                  message.nomeRemetente!,
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 12,
                    color: Theme.of(context).colorScheme.onSurfaceVariant,
                  ),
                ),
              ),
            Text(
              message.isInactive ? 'Mensagem apagada' : (message.texto ?? ''),
              style: TextStyle(
                color: message.isInactive
                    ? Theme.of(context).colorScheme.onSurface.withOpacity(0.6)
                    : (isMe ? Theme.of(context).colorScheme.onPrimary : Theme.of(context).colorScheme.onSurfaceVariant),
                fontStyle: message.isInactive ? FontStyle.italic : FontStyle.normal,
              ),
            ),
          ],
        ),
      ),
    );

    if (canDelete) {
      return Dismissible(
        key: ValueKey(message.id),
        direction: isMe ? DismissDirection.endToStart : DismissDirection.startToEnd,
        confirmDismiss: (direction) async {
          return await showDialog(
            context: context,
            builder: (BuildContext context) {
              return AlertDialog(
                title: const Text('Apagar mensagem'),
                content: const Text('Tem certeza que deseja apagar esta mensagem?'),
                actions: <Widget>[
                  TextButton(
                    onPressed: () => Navigator.of(context).pop(false),
                    child: const Text('Cancelar'),
                  ),
                  TextButton(
                    onPressed: () => Navigator.of(context).pop(true),
                    child: const Text('Apagar'),
                  ),
                ],
              );
            },
          );
        },
        onDismissed: (direction) {
          onDelete();
        },
        background: Container(
          color: Theme.of(context).colorScheme.error,
          alignment: isMe ? Alignment.centerRight : Alignment.centerLeft,
          padding: const EdgeInsets.symmetric(horizontal: 20),
          child: const Icon(Icons.delete, color: Colors.white),
        ),
        child: bubble,
      );
    }

    return bubble;
  }
}
