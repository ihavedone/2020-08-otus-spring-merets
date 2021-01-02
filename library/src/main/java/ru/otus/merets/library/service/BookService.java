package ru.otus.merets.library.service;

import ru.otus.merets.library.domain.Book;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Book getBookById(String id);
    List<Book> getAll();
    void save(Book book);
    void delete(Book book);
}
