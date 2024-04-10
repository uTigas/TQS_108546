# Use the MySQL 8.3.0 base image
FROM mysql:8.0

# Environment variables
ENV MYSQL_DATABASE=hw1
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password
ENV MYSQL_ROOT_PASSWORD=adminpassword

# Expose the MySQL port
EXPOSE 3306

# Copy the Initial Data into the container mysql secure files folder
COPY *.csv /var/lib/mysql-files/

# Copy the SQL script into the container
COPY init.sql /docker-entrypoint-initdb.d/init.sql

# Start MySQL service
CMD ["mysqld"]
