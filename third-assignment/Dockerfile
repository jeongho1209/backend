FROM eclipse-temurin:17-jre-focal

ADD build/libs/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]