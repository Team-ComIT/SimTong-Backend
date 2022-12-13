FROM eclipse-temurin:17.0.3_7-jre-focal

EXPOSE 8080
ENV TZ=Asia/Seoul

HEALTHCHECK --interval=10s --timeout=5s --retries=4 CMD curl -f http://localhost:8080 || exit 1

COPY ./simtong-infrastructure/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]