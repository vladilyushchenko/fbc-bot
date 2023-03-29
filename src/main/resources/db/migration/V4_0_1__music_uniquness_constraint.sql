ALTER TABLE music
    ADD CONSTRAINT title_author_uniqueness
        UNIQUE(title, author);