CREATE SEQUENCE IF NOT EXISTS SEQ_USER START 1;

ALTER TABLE users
    ALTER COLUMN user_name DROP NOT NULL;