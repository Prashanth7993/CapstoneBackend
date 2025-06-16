FROM maven AS build 

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-oracle


COPY --from=build ./target/notification-service.jar notification-service.jar

CMD ["java","-jar","notification-service.jar"]
