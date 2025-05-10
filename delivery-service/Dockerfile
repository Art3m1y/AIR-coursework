FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
ARG JAR_FILE=delivery-service.jar
COPY --from=build /app/target/*.jar ${JAR_FILE}
ENTRYPOINT ["java","-jar","/app/delivery-service.jar"]