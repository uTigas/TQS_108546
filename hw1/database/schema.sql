CREATE TABLE connection (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bus_id BIGINT,
    origin_id BIGINT,
    destination_id BIGINT,
    distance DOUBLE,
    price DOUBLE,
    departure TIME,
    arrival TIME,
    FOREIGN KEY (bus_id) REFERENCES Bus(id),
    FOREIGN KEY (origin_id) REFERENCES Terminal(id),
    FOREIGN KEY (destination_id) REFERENCES Terminal(id)
);
