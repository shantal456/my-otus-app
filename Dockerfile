FROM openjdk:24-jdk-slim

WORKDIR /app
COPY target/my-otus-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
LABEL authors="elenagaponova"

ENTRYPOINT ["java", "-jar", "app.jar"]