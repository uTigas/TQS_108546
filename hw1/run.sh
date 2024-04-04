#!/bin/bash

# Check if arguments are provided
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <image_name> <container_name>"
    exit 1
fi

# Assign arguments to variables
image_name=$1
container_name=$2
dockerfile_path="database"

cd "$dockerfile_path"

# Build the Database Docker image
docker build -t "$image_name" .

# Run the Database Docker container
docker run -d -p 3306:3306 --name "$container_name" "$image_name" --local-infile=1

cd ".."

