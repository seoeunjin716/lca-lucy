FROM eclipse-temurin:21-jdk-ubi9-minimal
VOLUME /tmp
COPY ./build/libs/*.jar discovery-server.jar
ENTRYPOINT ["java", "-jar", "/discovery-server.jar"]

