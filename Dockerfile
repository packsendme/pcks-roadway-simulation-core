
FROM openjdk:8-jdk-alpine
EXPOSE 9098
COPY /target/pcks-roadway-simulation-core-0.0.1-SNAPSHOT.jar pcks-roadway-simulation-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/pcks-roadway-simulation-core-0.0.1-SNAPSHOT.jar"]