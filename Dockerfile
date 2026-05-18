FROM eclipse-temurin:24-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
COPY --from=build /app/target/quarkus-app /app/target/quarkus-app

EXPOSE 10000
ENV QUARKUS_HTTP_PORT=10000

CMD ["java", "-jar", "target/quarkus-app/quarkus-run.jar"]