FROM eclipse-temurin:21-jdk-ubi9-minimal
VOLUME /tmp
COPY ./build/libs/*.jar user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]