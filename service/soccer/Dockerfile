FROM eclipse-temurin:21-jdk-ubi9-minimal
VOLUME /tmp
COPY ./build/libs/*.jar soccer-service.jar
ENTRYPOINT ["java", "-jar", "/soccer-service.jar"]

