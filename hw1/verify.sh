#!/bin/bash

mvn -f fs_webApp/pom.xml -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=uTigas_TQS_108546

