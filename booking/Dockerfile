FROM maven:slim as builder
WORKDIR /app

COPY . .
RUN mvn clean install

FROM openjdk:8-slim 

WORKDIR /app
COPY --from=builder /app/booking-0.0.1.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/booking-0.0.1.jar"]