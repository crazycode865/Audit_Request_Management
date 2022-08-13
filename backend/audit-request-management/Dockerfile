#FROM openjdk:11
#EXPOSE 9090
#COPY /target/audit-request-management-0.0.1-SNAPSHOT.jar arm-api.jar
#ENTRYPOINT ["java","-jar","/arm-api.jar"]

FROM maven:3.8.2-jdk-11

WORKDIR /audit-request-management
COPY . .
RUN mvn clean install -DskipTests=true

CMD mvn spring-boot:run