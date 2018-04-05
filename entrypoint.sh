#!/bin/bash
set -e

SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-dev}

# Start account-service application with specific JVM_ARGS and SPRING_PROFILE
java ${JVM_ARGS} -jar -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} ${ADDRESS_HOME}/address.jar
