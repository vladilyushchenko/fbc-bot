CREATE SEQUENCE IF NOT EXISTS SEQ_USER START 1;

CREATE TABLE IF NOT EXISTS daily_cock_sizes
(
    user_id    BIGINT PRIMARY KEY,
    size       BIGINT    NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);