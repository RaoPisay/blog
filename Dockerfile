# Use Eclipse Temurin JRE 22 as the base image
FROM eclipse-temurin:22-jre-jammy AS final

# Create a non-privileged user that the app will run under.
# See https://docs.docker.com/go/dockerfile-user-best-practices/
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

# Switch to the created non-privileged user
USER appuser

# Set the working directory
WORKDIR /app

# Copy the executable jar file into the container
COPY build/libs/support-0.0.1-SNAPSHOT.jar support-0.0.1-SNAPSHOT.jar

#copy wait-for-it script for mysql ip resolution
COPY wait-for-it.sh /wait-for-it.sh

# Expose the port the application will use
EXPOSE 8080

CMD ["sh"]

# Set the ENTRYPOINT to run the jar file
ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "support-0.0.1-SNAPSHOT.jar"]