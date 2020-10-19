package ru.otus.merets.library.repository;

import ru.otus.merets.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(Long id);
    void delete(Comment comment);
    Comment save(Comment comment);
    List<Comment> findAllByBookId(Long book_id);
    List<Comment> findAll();
}
