ARG DOCKER_USERNAME = 'OsvaldoNovinski'
ARG DOCKER_PASSWORD = 'Jh@137955'

FROM openjdk:11

# Configure a autenticação para o Docker Hub
RUN echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# Resto do seu Dockerfile

FROM openjdk:11
COPY . .
RUN mvn clean package -DskipTests

COPY --from=build /app/target/escribaJava.jar /escribaJava.jar
EXPOSE 8080
CMD ["java", "-jar", "/escribaJava.jar"]