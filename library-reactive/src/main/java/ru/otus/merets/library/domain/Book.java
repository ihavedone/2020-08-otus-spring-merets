package ru.otus.merets.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String caption;

    private Set<Author> authors;

    private Set<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(caption, book.caption) && (
                Objects.equals(authors, book.authors) || authors == null && book.authors.size() == 0 || book.authors == null && authors.size() == 0
        ) && (
                Objects.equals(genres, book.genres) || genres == null && book.genres.size() == 0 || book.genres == null && genres.size() == 0
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caption, authors, genres);
    }

    public static Book EMPTY = new Book(null, null, null, null);
}
