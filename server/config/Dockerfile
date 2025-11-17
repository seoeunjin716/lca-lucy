FROM eclipse-temurin:21-jdk-ubi9-minimal
VOLUME /tmp
COPY ./build/libs/*.jar config-server.jar
ENTRYPOINT ["java", "-jar", "/config-server.jar"]

