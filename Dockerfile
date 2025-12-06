FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

EXPOSE 3000
CMD ["java", "-jar", "myapp.jar"]