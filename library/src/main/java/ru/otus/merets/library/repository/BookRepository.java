package ru.otus.merets.library.repository;

import ru.otus.merets.library.domain.Book;

import java.util.Optional;
import java.util.Set;

public interface BookRepository {
    Book save(Book book);
    Set<Book> findAll();
    Optional<Book> findById(Long id);
    void delete(Book book);
}
