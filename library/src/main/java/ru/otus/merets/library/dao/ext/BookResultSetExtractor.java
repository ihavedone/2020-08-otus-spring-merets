package ru.otus.merets.library.dao.ext;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookResultSetExtractor implements ResultSetExtractor<Map<Long, Book>> {

    @Override
    public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<Long, Book> books = new HashMap<>();
        Book book = null;

        while (rs.next()) {
            long book_id = rs.getLong("book_id");
            if (!books.containsKey(book_id)) {
                book = new Book(
                        rs.getLong("book_id"),
                        rs.getString("book_caption"),
                        new HashSet<>(),
                        new HashSet<>());
                books.put(book.getId(), book);
            }
            if(rs.getLong("author_id")>0){
                book.getAuthors().add(new Author(rs.getLong("author_id"), rs.getString("author_name")));
            }
            if(rs.getLong("genre_id")>0) {
                book.getGenres().add(new Genre(rs.getLong("genre_id"), rs.getString("genre_name")));
            }
        }
        return books;
    }
}
