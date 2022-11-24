FROM openjdk:17-jre-alpine

EXPOSE 8080
ENV TZ=Asia/Seoul

COPY ./simtong-infrastructure/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]