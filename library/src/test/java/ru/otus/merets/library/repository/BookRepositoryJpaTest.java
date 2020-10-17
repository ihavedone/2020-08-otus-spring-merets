package ru.otus.merets.library.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Genre;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookRepositoryJpa should ")
@DataJpaTest
@Import({BookRepositoryJpa.class})
class BookRepositoryJpaTest {
    private static final String TEST_BOOK_NAME = "TestBook";
    private static final String NEW_BOOK_CAPTION = "New caption";
    private static final Long ID_TO_DELETE = 1L;
    private static final Long ID_TO_UPDATE = 1L;
    private static final Long ID_TO_SELECT = 1L;
    private static final Integer AMOUNT_OF_BOOKS_IN_TABLE = 5;

    @Autowired
    private BookRepositoryJpa bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("pass migrations regarding books")
    void shouldPassMigrationsViaFlyWayRegardingBooks() {
        assertThat(bookRepository.findAll().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("pass migrations regarding authors")
    void shouldPassMigrationsViaFlyWayRegardingAuthors() {
        assertThat(bookRepository.findById(1L).get().getAuthors().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("pass migrations regarding genres")
    void shouldPassMigrationsViaFlyWayRegardingGenres() {
        assertThat(bookRepository.findById(1L).get().getGenres().size()).isEqualTo(3);
    }

    @Test
    @DisplayName(" insert new book without genres and authors")
    void shouldInsertWithoutGenresAndAuthors() {
        Book book = new Book(0L, TEST_BOOK_NAME, new HashSet<>(), new HashSet<>(), new ArrayList<>());
        bookRepository.save(book);
        assertThat(bookRepository.findById(book.getId()).get()).isEqualTo(book);
    }

    @Test
    @DisplayName("get the book by id")
    void shouldGetBookById() {
        Book book = new Book(1L,
                "TestBook1",
                new HashSet<>(Arrays.asList(
                        new Author(1L, "TestAuthor1"),
                        new Author(2L, "TestAuthor2"),
                        new Author(3L, "TestAuthor3")
                )),
                new HashSet<>(Arrays.asList(
                        new Genre(1L, "TestGenre1"),
                        new Genre(2L, "TestGenre2"),
                        new Genre(3L, "TestGenre3")
                )),
                new ArrayList<>());
        assertThat(bookRepository.findById(1L).get()).isEqualTo(book);
    }

    @Test
    @DisplayName("return all books")
    void shouldReturnAllBooks() {
        assertThat(bookRepository.findAll().size()).isEqualTo(AMOUNT_OF_BOOKS_IN_TABLE);
    }

    @Test
    @DisplayName("delete book by id")
    void shouldDeleteBook() {
        assertThat(bookRepository.findById(ID_TO_DELETE)).isNotNull();
        bookRepository.delete(bookRepository.findById(ID_TO_DELETE).get());
        assertThat(bookRepository.findById(ID_TO_DELETE).isEmpty()).isTrue();
    }

    @Test
    @DisplayName("update the book")
    void shouldUpdateBook() {
        Optional<Book> book = bookRepository.findById(ID_TO_UPDATE);
        assertThat(book.get().getCaption()).isNotEqualTo(NEW_BOOK_CAPTION);
        Book newBook = new Book(book.get().getId(), NEW_BOOK_CAPTION, book.get().getAuthors(), book.get().getGenres(), new ArrayList<>());
        bookRepository.save(newBook);
        assertThat(bookRepository.findById(ID_TO_UPDATE).get().getCaption()).isEqualTo(NEW_BOOK_CAPTION);
    }

    @Test
    @DisplayName("load book correctly")
    void shouldLoadBookCorrectly() {
        val actualBook = em.find(Book.class, ID_TO_SELECT);
        val expectedBook = bookRepository.findById(ID_TO_SELECT);
        assertThat(expectedBook).isPresent().get().isEqualToComparingFieldByField(actualBook);
    }
}