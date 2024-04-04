#!/bin/bash

# Check if argument is provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <container_name>"
    exit 1
fi

# Assign argument to variable
container_name=$1

# Stop and remove Docker container
docker stop "$container_name" && docker rm "$container_name"

# Remove associated Docker image
docker rmi "$(docker images --format '{{.Repository}}:{{.Tag}}' | grep "$container_name")"

# Clean up associated volumes
docker volume prune -f

# Clear Docker cache
# docker system prune -af
