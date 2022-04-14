CREATE SEQUENCE IF NOT EXISTS SEQ_USER START 1;

CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT PRIMARY KEY,
    telegram_id BIGINT       NOT NULL,
    user_name    VARCHAR(255) NOT NULL,
    first_name  VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,
    nickname    VARCHAR(255),
    user_status VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL
);