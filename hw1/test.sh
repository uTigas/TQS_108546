#!/bin/bash

mvn -f ./fs_webApp/pom.xml test

# Check the exit code of the mvn test command
if [ $? -ne 0 ]; then
    echo "mvn test failed"
    exit 1
fi