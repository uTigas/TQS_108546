LOAD DATA INFILE '/var/lib/mysql-files/buses.csv' 
INTO TABLE bus 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS (`id`);

LOAD DATA INFILE '/var/lib/mysql-files/currency.csv' 
INTO TABLE currency 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS (`id`, `currency`);

-- Insert seats for each bus
INSERT INTO seat (bus_id, seat_number)
SELECT id, number
FROM bus
CROSS JOIN (
    SELECT @number := @number + 1 AS number
    FROM information_schema.tables, (SELECT @number := 0) AS t
    LIMIT 53
) AS seat_numbers;

LOAD DATA INFILE '/var/lib/mysql-files/terminals.csv' 
INTO TABLE terminal 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS (`name`, `longitude`, `latitude`, `city`, `description`);

LOAD DATA INFILE '/var/lib/mysql-files/connections.csv' 
INTO TABLE connection 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS (`id`, `bus_id`, `origin_name`, `destination_name`,`distance`,`price`,`departure`,`arrival`);