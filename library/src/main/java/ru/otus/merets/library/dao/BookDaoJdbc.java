package ru.otus.merets.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.merets.library.dao.ext.BookResultSetExtractor;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Genre;
import ru.otus.merets.library.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedJdbc;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;


    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("caption", book.getCaption());
        KeyHolder kh = new GeneratedKeyHolder();
        namedJdbc.update("INSERT INTO book (`caption`) VALUES (:caption)", params, kh);
        book.setId(kh.getKey().longValue());

        insertBooksAuthors(book);
        insertBooksGenres(book);

        return book.getId();
    }

    @Override
    public Book getById(long id) {
        Map<Long, Book> books = namedJdbc.query("SELECT " +
                        "b.id book_id, " +
                        "b.caption book_caption, " +
                        "a.id author_id, " +
                        "a.name author_name, " +
                        "g.id genre_id, " +
                        "g.name genre_name " +
                        "FROM book b LEFT JOIN books_authors ba ON ba.book_id = b.id " +
                        "LEFT JOIN author a ON a.id = ba.author_id " +
                        "LEFT JOIN books_genres bg ON bg.book_id = b.id " +
                        "LEFT JOIN genre g ON g.id = bg.genre_id " +
                        "WHERE b.id=:id",
                Map.of("id", id),
                new BookResultSetExtractor());
        return books
                .values()
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>(namedJdbc.query("SELECT " +
                        "b.id, " +
                        "b.caption " +
                        "FROM book b",
                new BookDaoJdbc.BookMapper()));
        Set<Author> authors = authorDao.getAll();
        Set<Genre> genres = genreDao.getAll();

        List<Pair<Long, Long>> books_authors = namedJdbc.query("SELECT " +
                        "b.id key, " +
                        "ba.author_id value " +
                        "FROM book b LEFT JOIN books_authors ba ON ba.book_id = b.id ",
                new KeyValueMapper());

        List<Pair<Long, Long>> books_genres = namedJdbc.query("SELECT " +
                        "b.id key, " +
                        "bg.genre_id value " +
                        "FROM book b LEFT JOIN books_genres bg ON bg.book_id = b.id ",
                new KeyValueMapper());

        books.forEach((b) -> {
            b.getAuthors().addAll(
                    authors
                            .stream()
                            .filter((a) -> books_authors
                                    .stream()
                                    .filter((p) -> {
                                        return p.getKey() == b.getId();
                                    })
                                    .map(Pair::getValue)
                                    .collect(Collectors.toList())
                                    .contains(a.getId()))
                            .collect(Collectors.toList()));
            b.getGenres().addAll(
                    genres
                            .stream()
                            .filter((a) -> books_genres
                                    .stream()
                                    .filter((p) -> {
                                        return p.getKey() == b.getId();
                                    })
                                    .map(Pair::getValue)
                                    .collect(Collectors.toList())
                                    .contains(a.getId()))
                            .collect(Collectors.toList()));
        });
        return books;
    }

    @Override
    public void delete(long id) {
        namedJdbc.update("DELETE FROM book b WHERE b.id=:id", Map.of("id", id));
    }

    @Override
    @Transactional
    public void update(Book book) {
        namedJdbc.update("UPDATE book b SET b.caption=:caption WHERE b.id=:id",
                Map.of(
                        "caption", book.getCaption(),
                        "id", book.getId()
                ));
        namedJdbc.update("DELETE FROM books_genres bg WHERE bg.book_id=:id",
                Map.of("id", book.getId()));
        namedJdbc.update("DELETE FROM books_authors ba WHERE ba.book_id=:id",
                Map.of("id", book.getId()));
        insertBooksAuthors(book);
        insertBooksGenres(book);
    }

    private void insertBooksAuthors(Book book) {
        if (book.getAuthors() == null) {
            return;
        }
        List<Long> authorsIds = book.getAuthors().stream().map(Author::getId).collect(Collectors.toList());
        authorsIds.forEach((a) -> {
            MapSqlParameterSource authorParams = new MapSqlParameterSource();
            authorParams.addValue("book_id", book.getId());
            authorParams.addValue("author_id", a);
            namedJdbc.update("INSERT INTO books_authors (`book_id`,`author_id`) VALUES (:book_id,:author_id)", authorParams);
        });
    }

    private void insertBooksGenres(Book book) {
        if (book.getGenres() == null) {
            return;
        }
        List<Long> genresIds = book.getGenres().stream().map(Genre::getId).collect(Collectors.toList());
        genresIds.forEach((a) -> {
            MapSqlParameterSource genreParams = new MapSqlParameterSource();
            genreParams.addValue("book_id", book.getId());
            genreParams.addValue("genre_id", a);
            namedJdbc.update("INSERT INTO books_genres (`book_id`,`genre_id`) VALUES (:book_id,:genre_id)", genreParams);
        });
    }

    private static final class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String caption = resultSet.getString("caption");
            return new Book(id, caption, new HashSet<>(), new HashSet<>());
        }
    }

    private static final class KeyValueMapper implements RowMapper<Pair<Long, Long>> {
        @Override
        public Pair<Long, Long> mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Pair<>(resultSet.getLong("key"), resultSet.getLong("value"));
        }
    }

}
