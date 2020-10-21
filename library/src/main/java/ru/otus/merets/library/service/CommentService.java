package ru.otus.merets.library.service;

import ru.otus.merets.library.domain.Comment;

public interface CommentService {
    void add();
    void delete();
    void print();
    Comment getViaUI();
}
