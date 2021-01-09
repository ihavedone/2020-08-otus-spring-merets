package ru.otus.merets.library.service;

import ru.otus.merets.library.domain.Comment;

public interface CommentService {
    void add(String book_id);
    void delete();
    void print();
    Comment getViaUI();
}
