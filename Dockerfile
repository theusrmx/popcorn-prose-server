FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk
RUN apt-get install -y maven
RUN mvn clean install -DskipTests
FROM openjdk:17-jdk-slim
COPY --from=build /target/deploy_render-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
