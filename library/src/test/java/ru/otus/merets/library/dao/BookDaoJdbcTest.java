package ru.otus.merets.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Genre;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookDaoJdbc should ")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {
    private static final String TEST_BOOK_NAME = "TestBook";
    private static final String NEW_BOOK_CAPTION = "New caption";
    private static final Long ID_TO_DELETE = 1L;
    private static final Long ID_TO_UPDATE = 1L;
    private static final Integer AMOUNT_OF_BOOKS_IN_TABLE = 5;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    @DisplayName("pass migrations regarding books")
    void passMigrationsViaFlyWayRegardingBooks(){
        assertThat(bookDaoJdbc.getAll().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("pass migrations regarding authors")
    void passMigrationsViaFlyWayRegardingAuthors(){
        assertThat(bookDaoJdbc.getById(1).getAuthors().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("pass migrations regarding genres")
    void passMigrationsViaFlyWayRegardingGenres(){
        assertThat(bookDaoJdbc.getById(1).getGenres().size()).isEqualTo(3);
    }

    @Test
    @DisplayName(" insert new book without genres and authors")
    void insertWithoutGenresAndAuthors() {
        Book book = new Book(null, TEST_BOOK_NAME, null, null);
        book.setId( bookDaoJdbc.insert(book) );
        assertThat( bookDaoJdbc.getById(book.getId() ) ).isEqualTo( book );
    }

    @Test
    @DisplayName(" insert new book with genres and authors")
    void insertWithGenresAndAuthors() {
        Genre genre = new Genre(1L, "TestGenre1");
        Author author = new Author(1L, "TestAuthor1");
        Book book = new Book(null, TEST_BOOK_NAME,
                new HashSet<>(Collections.singletonList(author)),
                new HashSet<>(Collections.singletonList(genre)));
        book.setId( bookDaoJdbc.insert(book) );
        assertThat( bookDaoJdbc.getById(book.getId()) ).isEqualTo( book );
    }


    @Test
    @DisplayName("get the book by id")
    void getById() {
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
                )) );
        assertThat(bookDaoJdbc.getById(1L)).isEqualTo(book);
    }

    @Test
    @DisplayName("return all books")
    void getAll() {
        assertThat( bookDaoJdbc.getAll().size() ).isEqualTo( AMOUNT_OF_BOOKS_IN_TABLE );
        assertThat( bookDaoJdbc.getAll().get(0).getCaption() ).isEqualTo( "TestBook1" );
        assertThat( bookDaoJdbc.getAll().get(AMOUNT_OF_BOOKS_IN_TABLE-1).getCaption() ).isEqualTo( "TestBook5" );
    }

    @Test
    @DisplayName("delete book by id")
    void delete() {
        assertThat( bookDaoJdbc.getById(ID_TO_DELETE) ).isNotNull();
        bookDaoJdbc.delete(ID_TO_DELETE);
        assertThat( bookDaoJdbc.getById(ID_TO_DELETE) ).isNull();
    }

    @Test
    @DisplayName("update the book")
    void update() {
        Book book = bookDaoJdbc.getById(ID_TO_UPDATE);
        assertThat( book.getCaption() ).isNotEqualTo(NEW_BOOK_CAPTION);
        Book newBook = new Book( book.getId(), NEW_BOOK_CAPTION, book.getAuthors(), book.getGenres() );
        bookDaoJdbc.update( newBook );
        assertThat( bookDaoJdbc.getById(ID_TO_UPDATE).getCaption() ).isEqualTo(NEW_BOOK_CAPTION);
    }
}