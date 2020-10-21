package ru.otus.merets.library.service;

import ru.otus.merets.library.domain.Book;

public interface BookService {
    void addNewBook();
    void deleteBook();
    void updateBook();
    void printListOfBooks();
    void printBook();
    Book select();
}
