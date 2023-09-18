
# Etapa 1: Construir o projeto
FROM maven:3.8.1-jdk-11 as builder

# Define o diretório de trabalho para /app
WORKDIR /app

# Copie o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Resolva as dependências e faça o cache delas
RUN mvn dependency:resolve

# Copie o código-fonte do projeto
COPY src ./src

# Compile o projeto
RUN mvn package -DskipTests

# Etapa 2: Preparar a imagem final
FROM adoptopenjdk/openjdk11:alpine
#FROM openjdk:11-slim

#COPY . /app
#WORKDIR /app

# Define o diretório de trabalho para /app
WORKDIR /app

# Copie o JAR gerado da etapa anterior
COPY --from=builder ../../escribaJava.jar /escribaJava.jar

# Exponha a porta 8080 (se o aplicativo usar essa porta)
EXPOSE 8080

# Execute o aplicativo
CMD ["java", "-jar", "/escribaJava.jar"]


