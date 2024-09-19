# Use an official OpenJDK image
FROM openjdk:11-jre-slim

# Create app directory and set it as the working directory
WORKDIR /usr/src/app

# Copy the JAR file into the container
COPY ./target/java-maven-app-1.0.jar /usr/src/app

# Expose the port the app runs on
EXPOSE 8080

# Command to run the JAR file
CMD ["java", "-jar", "java-maven-app-1.0.jar"]

