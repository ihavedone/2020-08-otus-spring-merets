package ru.otus.merets.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.merets.library.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByBook_Id(Long book_id);
}
