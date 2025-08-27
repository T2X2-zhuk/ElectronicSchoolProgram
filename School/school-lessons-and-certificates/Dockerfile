
FROM eclipse-temurin:21

# Install bash
# RUN apk add --no-cache bash

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY lessons-and-certificates-1.0.1.jar lessons-and-certificates.jar

CMD ["./wait-for-it.sh", "mysql-app:3306", "--", "java", "-Dspring.profiles.active=mysql-app", "-jar", "lessons-and-certificates.jar"]

EXPOSE 8080