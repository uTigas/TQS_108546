#!/bin/bash

# Check if arguments are provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <container_name>"
    exit 1
fi

# Assign arguments to variables
container_name=$1

# Run the Database Docker container
docker start "$container_name"

mvn -f ./fs_webApp/pom.xml spring-boot:run
