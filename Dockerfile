# Start with an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application files to the container
COPY . .

RUN chmod +x ./mvnw

# Build the application using Maven or Gradle
RUN ./mvnw clean package -DskipTests

# Expose the port that the application will use
EXPOSE 8080

# Run the application
CMD ["sh", "-c", "java -jar target/*.jar"]
