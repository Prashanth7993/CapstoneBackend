FROM maven AS build 

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-oracle


COPY --from=build ./target/route-service*.jar route-service.jar

CMD ["java","-jar","route-service.jar"]
