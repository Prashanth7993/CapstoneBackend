FROM maven as build 

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-oracle

WORKDIR /app

COPY --from=build /app/target/bus-service*.jar bus-service.jar

CMD ["java","-jar","bus-service.jar"]
