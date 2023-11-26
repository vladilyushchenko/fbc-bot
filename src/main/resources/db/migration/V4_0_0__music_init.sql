CREATE SEQUENCE IF NOT EXISTS SEQ_MUSIC START 1;

CREATE TABLE IF NOT EXISTS music
(
    id          BIGINT PRIMARY KEY,
    title       VARCHAR,
    author      VARCHAR(255),
    data_source VARCHAR(255),
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS users_music
(
    user_id  BIGINT NOT NULL,
    music_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, music_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (music_id) REFERENCES music (id)
);