FROM amazoncorretto:21-alpine-jdk
LABEL authors="Thomas"

COPY target/freezer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar" ,"/app.jar"]