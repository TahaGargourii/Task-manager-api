# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the project JAR file to the container
COPY target/TaskManager-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 9099

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
