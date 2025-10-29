-- Add AI column ID to the relational entities
CREATE TABLE percentage_history (
id INT PRIMARY KEY,
timestamp TIMESTAMP NOT NULL,
endpoint VARCHAR(50) NOT NULL,
params VARCHAR(50) NOT NULL,
response VARCHAR(100)
);