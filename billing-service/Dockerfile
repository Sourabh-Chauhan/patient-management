#FROM ubuntu:latest
#LABEL authors="Sourabh"

FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src


#ENV BILLING_SERVICE_GRPC_PORT=9005;
#ENV  JAVA_TOOL_OPTIONS=-agentlib:jdwp\\=transport\\=dt_socket,server\\=y,suspend\\=n,address\\=*:5005;
#ENV SPRING_DATASOURCE_PASSWORD=password;
#ENV SPRING_DATASOURCE_URL=jdbc:postgresql://patient-service-db:5432/db;
#ENV SPRING_DATASOURCE_USERNAME=admin_user;
#ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update;
#ENV SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092;
#ENV SPRING_SQL_INIT_MODE=always;
#ENV BILLING_SERVICE_ADDRESS=billing-service

#RUN mvn clean package

# SKIP TESTS HERE so the build doesn't look for a DB
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine AS runner

WORKDIR /app

COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4001
EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]