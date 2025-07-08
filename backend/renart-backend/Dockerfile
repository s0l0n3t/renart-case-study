FROM openjdk:17-jdk-alpine
# Create and set working directory
WORKDIR /app
# Copy the built JAR from the builder stage
COPY  /target/renart-backend-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expose the application port
EXPOSE 8180

CMD ["java", "-jar", "/app/myapp.jar"]