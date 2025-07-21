FROM eclipse-temurin:21


COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh
COPY school-children-1.0.1.jar school-student.jar
CMD ["./wait-for-it.sh", "mysql-container2:3306", "--", "java", "-Dspring.profiles.active=mysql-container2", "-jar", "school-student.jar"]
EXPOSE 8080