FROM maven AS build 
WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests



FROM openjdk:17-oracle


COPY --from=build /app/target/authentication-service.jar authentication-service.jar

CMD ["java","-jar","authentication-service.jar"]
