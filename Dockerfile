## Use the official OpenJDK base image
#FROM openjdk:17-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the JAR file into the container
#COPY target/parking-lot-management-system-backend-1.0-SNAPSHOT.jar /app/app.jar
#
## Command to run the application
#CMD ["java", "-jar", "app.jar"]

# Use the official Maven image to build your application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download dependencies and build the application
RUN mvn dependency:go-offline && mvn package

# Use the official OpenJDK base image to run your application
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the container
COPY --from=build /app/target/parking-lot-management-system-backend-1.0-SNAPSHOT.jar /app/app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
