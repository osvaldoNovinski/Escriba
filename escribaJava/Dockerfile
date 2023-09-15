
#ARG DOCKER_USERNAME=OsvaldoNovinski
#ARG DOCKER_PASSWORD=Jh@137955

FROM maven:3.6.3-jdk-11-slim

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download all the dependencies and cache them
RUN mvn dependency:resolve

# Copy the project source code
COPY src ./src

# Build the project
RUN mvn package -DskipTests

# Create a new image with only the necessary files
FROM openjdk:11

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file from the previous image
COPY --from=0 /app/target/escribaJava.jar /escribaJava.jar

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/escribaJava.jar"]
