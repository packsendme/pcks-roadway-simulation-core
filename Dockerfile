
FROM openjdk:8-jdk-alpine
EXPOSE 9098
COPY /target/packsendme-roadway-sa-server-0.0.1-SNAPSHOT.jar packsendme-roadway-sa-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/packsendme-roadway-sa-server-0.0.1-SNAPSHOT.jar"]