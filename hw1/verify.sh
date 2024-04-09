# Check if arguments are provided
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <image_name> <container_name>"
    exit 1
fi

# Assign arguments to variables
image_name=$1
container_name=$2
dockerfile_path="database"

./clean.sh "$container_name"
#!/bin/bash

# Build the Docker image
docker build -t "$image_name" "$dockerfile_path"

# Run the Database Docker container
docker run -d -p 3306:3306 --name "$container_name" "$image_name"

sleep 7
mvn mvn -f fs_webApp/pom.xml -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=uTigas_TQS_108546

# Check the exit code of the mvn test command
if [ $? -ne 0 ]; then
    echo "mvn test failed"
    exit 1
fi