FROM openjdk:13-ea-19-jdk-alpine

MAINTAINER Navaneeth Thandavamurthy "navaneeth.gowda@idexcel.net"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY ./target/admin-service-0.0.1-SNAPSHOT.jar webapp.jar

CMD ["java" , "-jar", "webapp.jar"]

 