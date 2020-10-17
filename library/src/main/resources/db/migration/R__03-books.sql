INSERT INTO books (id, caption)
VALUES (1, 'War and Peace'),
       (2, 'Programming: Principles and Practice Using C++ (2nd Edition)'),
       (3, 'The Idiot'),
       (4, 'Harry Potter'),
       (5, 'Beyond Good and Evil: The Philosophy Classic'),
       (6, 'Linux Kernel Internals (2nd Edition)');

INSERT INTO books_genres (book_id, genre_id)
VALUES (1, 2),
       (1, 3),
       (1, 7),
       (2, 5),
       (3, 2),
       (3, 7),
       (4, 4),
       (6, 5),
       (5, 6);

INSERT INTO books_authors (book_id, author_id)
VALUES (1, 1),
       (2, 2),
       (3, 5),
       (4, 3),
       (5, 4),
       (6, 6),
       (6, 7),
       (6, 8),
       (6, 9),
       (6, 10),
       (6, 11);




