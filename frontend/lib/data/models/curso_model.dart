import 'package:freezed_annotation/freezed_annotation.dart';

part 'curso_model.freezed.dart';
part 'curso_model.g.dart';

@freezed
class CursoModel with _$CursoModel {
  const CursoModel._();

  const factory CursoModel({
    int? id,
    String? nome,
    String? codigoSuap,
    String? status,
  }) = _CursoModel;

  factory CursoModel.fromJson(Map<String, dynamic> json) => _$CursoModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
