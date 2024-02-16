# Use the official OpenJDK base image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/parking-lot-management-system-backend-1.0-SNAPSHOT.jar /app/app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
