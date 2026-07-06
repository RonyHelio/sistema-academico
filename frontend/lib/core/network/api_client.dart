import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'auth_interceptor.dart';
import 'error_interceptor.dart';
import '../errors/error_notifier.dart';

// Provedor para obter o token que será preenchido pelo módulo de autenticação futuramente.
final tokenProvider = Provider<Future<String?> Function()>((ref) {
  return () async => null;
});

final apiClientProvider = Provider<Dio>((ref) {
  final dio = Dio(BaseOptions(
    baseUrl: 'http://localhost:8080/api', // Substitua pela URL da sua API
    connectTimeout: const Duration(seconds: 10),
    receiveTimeout: const Duration(seconds: 10),
    contentType: 'application/json',
  ));

  final getToken = ref.watch(tokenProvider);
  final errorNotifier = ref.watch(errorNotifierProvider.notifier);

  dio.interceptors.addAll([
    AuthInterceptor(getToken: getToken),
    ErrorInterceptor(
      onErrorCallback: (appError) {
        errorNotifier.notifyError(appError);
      },
    ),
    LogInterceptor(
      request: true,
      requestHeader: true,
      requestBody: true,
      responseHeader: true,
      responseBody: true,
      error: true,
    ),
  ]);

  return dio;
});
