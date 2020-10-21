package ru.otus.merets.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id = 0L;

    @Column(name = "caption")
    private String caption;

    @BatchSize(size = 10)
    @ManyToMany(targetEntity = Author.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name= "books_authors", joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="author_id"))
    private Set<Author> authors;

    @BatchSize(size = 10)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name= "books_genres", joinColumns = @JoinColumn(name="book_id"),inverseJoinColumns = @JoinColumn(name="genre_id"))
    private Set<Genre> genres;

//    @BatchSize(size = 20)
//    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "book_id")
//    List<Comment> comments;

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
}
