FROM openjdk:8-jdk-alpine
ARG WAR_FILE=target/*.war
COPY ${WAR_FILE} newsWebService.war
EXPOSE 80
ENTRYPOINT ["java","-jar","/newsWebService.war"]