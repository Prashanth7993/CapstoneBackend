# Stage 1: Build
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-oracle

WORKDIR /app

COPY --from=build /app/target/payment-service*.jar payment-service.jar

CMD ["java", "-jar", "payment-service.jar"]
