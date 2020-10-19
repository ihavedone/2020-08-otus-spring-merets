package ru.otus.merets.library.service;

import ru.otus.merets.library.domain.Comment;

public interface CommentService {
    void addComment();
    void deleteComment();
    void showComment();
    Comment select();
}
