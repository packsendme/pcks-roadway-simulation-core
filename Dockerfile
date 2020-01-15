
FROM java:8
EXPOSE 9098
ADD /target/packsendme-land-sa-server-0.0.1-SNAPSHOT.jar packsendme-land-sa-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/packsendme-land-sa-server-0.0.1-SNAPSHOT.jar"]

