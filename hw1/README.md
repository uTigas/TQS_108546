## Run Database
Database is hosted in a mysql instance running over docker.

To create the docker container, simply head to fs_webApp/database and run the following commands on your terminal:

    docker build -t mysql .

##### and

    docker run -d -p 3306:3306 --name mysql-container-name mysql 

by Tiago Pereira, nMec 108546
