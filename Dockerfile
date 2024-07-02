FROM maven:3.9.8-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-alpine-jdk
COPY --from=build /target/demo/spring-lib-web.jar demo.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","demo.jar" ]
 