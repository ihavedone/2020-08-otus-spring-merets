--create sql schema
CREATE TABLE books (
id BIGSERIAL PRIMARY KEY,
caption VARCHAR (64)
);

CREATE TABLE authors (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(64),
UNIQUE KEY authors_name_unique (name)
);

CREATE TABLE genres (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(64),
UNIQUE KEY genres_name_unique (name)
);

CREATE TABLE books_authors (
book_id BIGINT,
author_id BIGINT,
PRIMARY KEY (book_id,author_id),
CONSTRAINT books_authors_book_constraint FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT books_authors_author_constraint FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE books_genres (
book_id BIGINT,
genre_id BIGINT,
PRIMARY KEY (book_id,genre_id),
CONSTRAINT books_genres_book_constraint FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT books_genres_genre_constraint FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE ON UPDATE CASCADE
);