# ============================================================
# Stage 1: Build the application
# ============================================================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy POM first so dependency downloads are cached as a layer.
# This speeds up rebuilds when only source code changes.
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy source and build the jar (skip tests for faster image builds)
COPY src ./src
RUN mvn -B clean package -DskipTests

# ============================================================
# Stage 2: Runtime image (slim JRE only)
# ============================================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Create a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8181

# JVM tuning for containers
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]