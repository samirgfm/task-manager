FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/manager-1.0.0.jar /app/manager-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/manager-1.0.0.jar"]