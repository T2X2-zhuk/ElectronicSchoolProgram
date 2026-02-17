FROM eclipse-temurin:21

COPY school-student-1.0.1.jar school-student.jar

ENTRYPOINT ["sh", "-c", "sleep 20 && java -Dspring.profiles.active=mysql-container -jar school-student.jar"]

EXPOSE 8093