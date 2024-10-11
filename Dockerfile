# Use an official OpenJDK image
FROM openjdk:11-jre-slim

# Create app directory and set it as the working directory
WORKDIR /usr/src/app

# Copy the JAR file into the container
COPY ./target/java* /usr/src/app/myapp.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the JAR file
CMD ["java", "-jar", "myapp.jar"]

