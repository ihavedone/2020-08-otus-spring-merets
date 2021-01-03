package ru.otus.merets.library.service;

import ru.otus.merets.library.domain.Book;

import java.util.List;

public interface BookService {
    Book getBookById(String id);
    List<Book> getAll();
    Book save(Book book);
    void deleteById(String id);
}
