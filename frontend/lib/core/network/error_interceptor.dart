import 'package:dio/dio.dart';
import '../errors/app_error.dart';

class ErrorInterceptor extends Interceptor {
  final void Function(AppError) onErrorCallback;

  ErrorInterceptor({required this.onErrorCallback});

  @override
  void onError(DioException err, ErrorInterceptorHandler handler) {
    late AppError appError;

    switch (err.type) {
      case DioExceptionType.connectionTimeout:
      case DioExceptionType.sendTimeout:
      case DioExceptionType.receiveTimeout:
        appError = const AppError('Tempo de conexão esgotado. Tente novamente mais tarde.', code: 'TIMEOUT');
        break;
      case DioExceptionType.badResponse:
        final statusCode = err.response?.statusCode;
        final data = err.response?.data;
        String message = 'Ocorreu um erro inesperado.';
        
        if (data is Map<String, dynamic> && data['message'] != null) {
          message = data['message'];
        } else if (statusCode == 401) {
          message = 'Sessão expirada ou não autorizada. Faça login novamente.';
        } else if (statusCode == 403) {
          message = 'Você não tem permissão para acessar este recurso.';
        } else if (statusCode == 404) {
          message = 'Recurso não encontrado.';
        } else if (statusCode != null && statusCode >= 500) {
          message = 'Erro interno do servidor. Nossa equipe já foi notificada.';
        }

        appError = AppError(message, code: statusCode?.toString(), details: data);
        break;
      case DioExceptionType.cancel:
        appError = const AppError('Requisição cancelada.', code: 'CANCELLED');
        break;
      case DioExceptionType.connectionError:
        appError = const AppError('Sem conexão com a internet. Verifique sua rede.', code: 'CONNECTION_ERROR');
        break;
      case DioExceptionType.unknown:
      default:
        appError = const AppError('Ocorreu um erro desconhecido. Verifique sua conexão e tente novamente.', code: 'UNKNOWN');
        break;
    }

    onErrorCallback(appError);
    handler.next(err);
  }
}
