FROM maven AS build 

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-oracle


COPY --from=build ./target/feedback-service*.jar feedback-service.jar

CMD ["java","-jar","feedback-service.jar"]
