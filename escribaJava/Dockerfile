
# Etapa 1: Construir o projeto
FROM maven:3.8.1-jdk-11 as builder

# Define o diretório de trabalho para /app
RUN mkdir /app
WORKDIR /app

# Copie o arquivo pom.xml para o diretório de trabalho
COPY pom.xml ./pom.xml

# Resolva as dependências e faça o cache delas
RUN mvn dependency:resolve

# Copie o código-fonte do projeto
COPY src ./src

COPY target ./target

# Compile o projeto
#RUN mvn package -DskipTests

RUN mvn clean package -P build-docker -DskipTests
RUN ls /app/target

# Etapa 2: Preparar a imagem final
FROM openjdk:11-slim

WORKDIR /app

COPY --from=builder /app/target/escribaJava.jar /escribaJava.jar

# Exponha a porta 8080 (se o aplicativo usar essa porta)
EXPOSE 9564

# Execute o aplicativo
CMD ["java", "-jar", "/escribaJava.jar"]


