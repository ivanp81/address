# Set the base image to java8
FROM openjdk:8-alpine

# Define default environment variables
ENV ADDRESS_HOME=/opt/address
ENV ADDRESS_BINARIES=/opt/address/bin

# Create directory
RUN mkdir -p $ADDRESS_BINARIES

# Set default directory
WORKDIR $ADDRESS_HOME

# Copy address jar file
COPY target/*.jar $ADDRESS_HOME/address.jar

# Add initialization script
ADD entrypoint.sh $ADDRESS_BINARIES/entrypoint.sh

# Give permissions
RUN chmod 755 $ADDRESS_BINARIES/entrypoint.sh

# Expose default servlet container port
EXPOSE 8080

# Main command
ENTRYPOINT ["/bin/sh", "/opt/address/bin/entrypoint.sh"]