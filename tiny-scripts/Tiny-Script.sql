
-- 1. Create the database
CREATE DATABASE tiny_db;

-- Connect to the database
\c tiny_db;

-- 2. Create the schema
CREATE SCHEMA tiny_schema;

-- 3. Create a user with password
CREATE USER tiny_user WITH PASSWORD 'password';

-- 4. Grant privileges to the user on the schema
GRANT USAGE ON SCHEMA tiny_schema TO tiny_user;
GRANT CREATE ON SCHEMA tiny_schema TO tiny_user;

-- Optional: Allow full access on all objects within the schema
ALTER DEFAULT PRIVILEGES IN SCHEMA tiny_schema
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO tiny_user;

-- 5. Create the table inside the schema
CREATE TABLE tiny_schema.tiny_url (
    tiny_url_id SERIAL PRIMARY KEY,
    original_url TEXT NOT NULL,
    tiny_url VARCHAR(100) NOT NULL UNIQUE,
    expiration_date TIMESTAMP WITH TIME ZONE,
    active BOOLEAN NOT NULL,
    created_on TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_on TIMESTAMP WITH TIME ZONE
);
