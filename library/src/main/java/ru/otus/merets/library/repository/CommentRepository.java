package ru.otus.merets.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.merets.library.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {
}
