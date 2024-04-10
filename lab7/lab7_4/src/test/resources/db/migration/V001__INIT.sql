-- Create the schema for the Car entity
CREATE TABLE IF NOT EXISTS car (
    car_id BIGSERIAL PRIMARY KEY,
    maker VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL
);


-- Insert sample data
INSERT INTO car (maker, model) VALUES ('Toyota', 'Corolla');
INSERT INTO car (maker, model) VALUES ('Honda', 'Civic');
INSERT INTO car (maker, model) VALUES ('Ford', 'Fusion');
INSERT INTO car (maker, model) VALUES ('Chevrolet', 'Malibu');
INSERT INTO car (maker, model) VALUES ('Volkswagen', 'Jetta');
INSERT INTO car (maker, model) VALUES ('BMW', '3 Series');
INSERT INTO car (maker, model) VALUES ('Mercedes-Benz', 'C-Class');
INSERT INTO car (maker, model) VALUES ('Audi', 'A4');
INSERT INTO car (maker, model) VALUES ('Hyundai', 'Elantra');
INSERT INTO car (maker, model) VALUES ('Kia', 'Optima');
