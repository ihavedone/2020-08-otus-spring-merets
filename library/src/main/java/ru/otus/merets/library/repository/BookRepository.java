package ru.otus.merets.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.merets.library.domain.Book;

public interface BookRepository extends MongoRepository<Book,String> {
}
