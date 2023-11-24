FROM openjdk:17-jdk-alpine
RUN apk update && apk add --no-cache bash
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} task-app.jar
ENTRYPOINT [ "java", "-Dserver.port=${PORT}", "-jar", "task-app.jar" ]