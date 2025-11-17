FROM eclipse-temurin:21-jdk-ubi9-minimal
VOLUME /tmp
COPY ./build/libs/*.jar common-service.jar
ENTRYPOINT ["java", "-jar", "/common-service.jar"]

