package ru.otus.merets.library.service;

import ru.otus.merets.library.domain.Book;

public interface BookService {
    void add();
    void delete();
    void update();
    void printAll();
    void print();
    Book getViaUI();
}
