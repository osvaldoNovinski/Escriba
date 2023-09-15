FROM openjdk:11
COPY . .
RUN mvn clean package -DskipTests

COPY --from=build /app/target/escribaJava.jar /escribaJava.jar
EXPOSE 8080
CMD ["java", "-jar", "/escribaJava.jar"]