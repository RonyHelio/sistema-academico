import 'package:freezed_annotation/freezed_annotation.dart';

part 'aluno_model.freezed.dart';
part 'aluno_model.g.dart';

@freezed
class AlunoModel with _$AlunoModel {
  const AlunoModel._();

  const factory AlunoModel({
    int? id,
    String? nomeAluno,
    String? emailAluno,
    String? matricula,
    String? nomeCurso,
    String? status,
  }) = _AlunoModel;

  factory AlunoModel.fromJson(Map<String, dynamic> json) => _$AlunoModelFromJson(json);

  bool get isActive => status == 'A';
  bool get isInactive => status == 'I';
}
