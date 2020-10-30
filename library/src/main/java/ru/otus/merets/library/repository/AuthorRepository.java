package ru.otus.merets.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.merets.library.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
