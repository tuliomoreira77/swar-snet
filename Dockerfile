FROM openjdk:11.0.12-jre-slim

WORKDIR /usr/src/app

COPY /target/lib/. lib/.
COPY /target/swar-snet.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]



