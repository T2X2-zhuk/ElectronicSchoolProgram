FROM eclipse-temurin:21
COPY lessons-and-certificates-1.0.1.jar lessons-and-certificates.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=mysql-container", "-jar", "lessons-and-certificates.jar"]
EXPOSE 8092