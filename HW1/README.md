## Run Database
Database is hosted in a mysql instance running over docker.

To run it, simply head to fs_webApp/database and run the following commands on your terminal:


    docker build -t mysql
##### and
    docker run -d -p 3306:3306 --name mysql-container-name mysql 
