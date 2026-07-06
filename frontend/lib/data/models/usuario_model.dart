import 'package:freezed_annotation/freezed_annotation.dart';

part 'usuario_model.freezed.dart';
part 'usuario_model.g.dart';

@freezed
class UsuarioModel with _$UsuarioModel {
  const UsuarioModel._();

  const factory UsuarioModel({
    int? id,
    String? nome,
    String? email,
    String? login,
    String? perfil,
    DateTime? cadastro,
    String? status,
  }) = _UsuarioModel;

  factory UsuarioModel.fromJson(Map<String, dynamic> json) => _$UsuarioModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
