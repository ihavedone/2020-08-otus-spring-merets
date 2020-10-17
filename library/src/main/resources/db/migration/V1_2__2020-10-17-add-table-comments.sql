CREATE TABLE comments (
                         id BIGSERIAL PRIMARY KEY,
                         text VARCHAR(2048),
                         book_id BIGINT NOT NULL,
CONSTRAINT comments_constraint FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE ON UPDATE CASCADE
);