FROM eclipse-temurin:21-jdk-ubi9-minimal
VOLUME /tmp
COPY ./build/libs/*.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]

