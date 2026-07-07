FROM debian:bullseye-slim AS build-env

# Instala as dependências necessárias para o Flutter
RUN apt-get update && apt-get install -y curl git unzip xz-utils zip libglu1-mesa

# Clona o SDK do Flutter (branch stable)
RUN git clone https://github.com/flutter/flutter.git -b stable /usr/local/flutter
ENV PATH="/usr/local/flutter/bin:/usr/local/flutter/bin/cache/dart-sdk/bin:${PATH}"

WORKDIR /app
COPY . .

# Baixa as dependências e gera os arquivos do build_runner
RUN flutter pub get
RUN dart run build_runner build -d

# Inicializa o scaffolding para web (pois o projeto foi criado estaticamente)
RUN flutter create . --platforms web

# Compila o app para Web
RUN flutter build web

# Estágio de produção com Nginx
FROM nginx:alpine
# Copia o build estático para a pasta de arquivos HTML do Nginx
COPY --from=build-env /app/build/web /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
