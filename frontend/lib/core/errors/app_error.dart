class AppError implements Exception {
  final String message;
  final String? code;
  final dynamic details;

  const AppError(this.message, {this.code, this.details});

  @override
  String toString() {
    return 'AppError: $message (Código: $code)';
  }
}
