import 'package:freezed_annotation/freezed_annotation.dart';

part 'falta_model.freezed.dart';
part 'falta_model.g.dart';

@freezed
class FaltaModel with _$FaltaModel {
  const FaltaModel._();

  const factory FaltaModel({
    int? id,
    int? matriculaTurmaId,
    String? nomeAluno,
    String? nomeDisciplina,
    DateTime? data,
    int? quantidade,
    String? observacao,
    String? status,
  }) = _FaltaModel;

  factory FaltaModel.fromJson(Map<String, dynamic> json) => _$FaltaModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
