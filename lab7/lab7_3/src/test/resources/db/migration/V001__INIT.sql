-- Create the schema for the Book entity
CREATE TABLE IF NOT EXISTS book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    pub_year INT,
    Author VARCHAR(255)
);

-- Insert sample data into the Book table
INSERT INTO book (title, pub_year, Author) VALUES ('Sample Book 1', 2022, 'John Doe');
INSERT INTO book (title, pub_year, Author) VALUES ('Sample Book 2', 2023, 'Jane Smith');
INSERT INTO book (title, pub_year, Author) VALUES ('Sample Book 3', 2024, 'Alice Johnson');