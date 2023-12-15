# Use a base image with JDK and Maven pre-installed
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven Wrapper files (mvnw and .mvn) to the container
COPY mvnw .
COPY .mvn .mvn

# Copy the project files to the container
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B
COPY src src

# Build the application using Maven Wrapper
RUN ./mvnw clean package -DskipTests

EXPOSE 8082
# Set the entrypoint command to run the Spring Boot application
RUN cp "target/song-service-3.0.jar" app.jar
CMD ["java", "-jar", "app.jar"]