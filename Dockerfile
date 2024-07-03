FROM openjdk:21-oracle
ARG JAR_FILE=./target/*.jar
COPY ./target/library-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","/demo.jar" ]
 