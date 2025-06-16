FROM maven as build 

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-oracle


COPY --from=build ./target/bus-service*.jar bus-service.jar

CMD ["java","-jar","bus-service.jar"]
