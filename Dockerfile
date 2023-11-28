FROM ubuntu:latest AS build
RUN apt-get install openjdk-17-jdk -Y
RUN apt-get install maven -y
RUN mvn clean install
FROM openjdk:17-jdk-slim
COPY --from=build /target/deploy_render-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]