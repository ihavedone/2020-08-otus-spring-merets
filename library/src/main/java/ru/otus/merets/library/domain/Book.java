package ru.otus.merets.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Data
public class Book {
    private Long id;
    private final String caption;
    private final Set<Author> authors;
    private final Set<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(caption, book.caption) && (
                Objects.equals(authors, book.authors) || authors == null && book.authors.size() == 0 || book.authors == null && authors != null && authors.size() == 0
        ) && (
                Objects.equals(genres, book.genres) || genres == null && book.genres != null && book.genres.size() == 0 || book.genres == null && genres != null && genres.size() == 0
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caption, authors, genres);
    }
}
