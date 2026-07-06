import 'package:freezed_annotation/freezed_annotation.dart';

part 'nota_model.freezed.dart';
part 'nota_model.g.dart';

@freezed
class NotaModel with _$NotaModel {
  const NotaModel._();

  const factory NotaModel({
    int? id,
    int? matriculaTurmaId,
    String? nomeAluno,
    String? nomeDisciplina,
    String? descricao,
    double? valor,
    String? status,
  }) = _NotaModel;

  factory NotaModel.fromJson(Map<String, dynamic> json) => _$NotaModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
