FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/authenticator-2020.06.05.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar",
    "-DclientId=${CLIENT_ID}",
    "-DclientSecret=${CLIENT_SECRET}",
    "--spring.datasource.url=${URL}",
    "--spring.datasource.username=${USERNAME}",
    "--spring.datasource.password=${PASSWORD}" ]