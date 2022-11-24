FROM eclipse-temurin:17.0.3_7-jre-focal

EXPOSE 8080
ENV TZ=Asia/Seoul

COPY ./simtong-infrastructure/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]