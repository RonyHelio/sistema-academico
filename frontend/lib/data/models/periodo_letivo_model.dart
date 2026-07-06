import 'package:freezed_annotation/freezed_annotation.dart';

part 'periodo_letivo_model.freezed.dart';
part 'periodo_letivo_model.g.dart';

@freezed
class PeriodoLetivoModel with _$PeriodoLetivoModel {
  const PeriodoLetivoModel._();

  const factory PeriodoLetivoModel({
    int? id,
    int? ano,
    int? semestre,
    String? descricao,
    String? status,
  }) = _PeriodoLetivoModel;

  factory PeriodoLetivoModel.fromJson(Map<String, dynamic> json) => _$PeriodoLetivoModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
