
FROM java:8
EXPOSE 9098
ADD /target/packsendme-roadwaysa-server-0.0.1-SNAPSHOT.jar packsendme-roadwaysa-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/packsendme-roadwaysa-server-0.0.1-SNAPSHOT.jar"]

