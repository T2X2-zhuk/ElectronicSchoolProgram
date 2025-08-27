
FROM eclipse-temurin:21

# Install bash
# RUN apk add --no-cache bash

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY school-student-1.0.1.jar school-student.jar

CMD ["./wait-for-it.sh", "mysql-app:3306", "--", "java", "-Dspring.profiles.active=mysql-app", "-jar", "school-student.jar"]

EXPOSE 8080