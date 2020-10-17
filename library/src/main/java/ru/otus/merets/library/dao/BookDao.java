package ru.otus.merets.library.dao;

import ru.otus.merets.library.domain.Book;

import java.util.List;

public interface BookDao {
    long insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void delete(long id);

    void update(Book book);
}
