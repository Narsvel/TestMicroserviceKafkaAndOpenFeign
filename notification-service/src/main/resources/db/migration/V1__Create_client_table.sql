CREATE TABLE IF NOT EXISTS clients (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    age INT CHECK (age BETWEEN 0 AND 149),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);