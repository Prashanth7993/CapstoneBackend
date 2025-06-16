FROM maven AS build 

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-oracle


COPY --from=build ./target/carpool-service.jar carpool-service.jar

CMD ["java","-jar","carpool-service.jar"]
