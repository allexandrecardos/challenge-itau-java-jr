FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY . .

EXPOSE 8080

CMD [ "./mvnw", "spring-boot:run" ]
