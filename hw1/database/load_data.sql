LOAD DATA INFILE '/docker-entrypoint-initdb.d/initialData.csv'
INTO TABLE connection
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS; -- Skip header row
