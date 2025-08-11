/*
 * Copyright (c) 2025 Jyotirmay Gupta
 *
 * Project: Tiny URL
 * Description: This is a personal project by Jyotirmay Gupta that implements a
 * simplified URL shortening service, similar in concept to services like bit.ly or tinyurl.com.
 * It allows long URLs to be converted into compact, easy-to-share short URLs.
 *
 * This code is intended for educational and personal use, demonstrating core backend
 * concepts such as encoding algorithms, database storage, and URL mapping.
 *
 * Licensed under the Apache License Version 2.0. See LICENSE file for more details.
 */


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
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA tiny_schema TO tiny_user;

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