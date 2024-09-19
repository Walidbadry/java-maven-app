# Use an official OpenJDK image
FROM openjdk:11-jre-slim


# Copy the JAR file into the container
COPY ./target/java-maven-app-1.0-SNAPSHOT.jar /usr/app

WORKDIR /usr/app
# Expose the port the app runs on
EXPOSE 8080

# Command to run the JAR file
CMD ["java", "-jar", "java-maven-app-1.0-SNAPSHOT.jar"]

