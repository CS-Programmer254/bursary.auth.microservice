## Stage 1: Build the application
#FROM maven:latest AS build
#
## Set the working directory
#WORKDIR /app
#
## Copy the pom.xml and the source code
#COPY pom.xml .
#COPY src ./src
#
## Package the application
#RUN mvn clean package -DskipTests
#
## Stage 2: Run the application
#FROM openjdk:17-slim
#
## Set the working directory
#WORKDIR /app
#
## Copy the JAR file from the build stage
#COPY --from=build /app/target/bursary.auth.microservice*.jar app.jar
#
## Command to run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]
