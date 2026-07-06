FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
# Otimiza o cache das dependências do Maven
RUN mvn dependency:go-offline

COPY src ./src
# Compila o projeto pulando os testes para acelerar o build no Docker
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copia o JAR gerado no stage anterior
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
