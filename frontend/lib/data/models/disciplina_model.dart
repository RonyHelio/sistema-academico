import 'package:freezed_annotation/freezed_annotation.dart';

part 'disciplina_model.freezed.dart';
part 'disciplina_model.g.dart';

@freezed
class DisciplinaModel with _$DisciplinaModel {
  const DisciplinaModel._();

  const factory DisciplinaModel({
    int? id,
    String? nome,
    String? codigo,
    String? codigoSuap,
    int? cargaHoraria,
    String? status,
  }) = _DisciplinaModel;

  factory DisciplinaModel.fromJson(Map<String, dynamic> json) => _$DisciplinaModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
