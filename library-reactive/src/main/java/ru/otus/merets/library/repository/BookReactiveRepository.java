package ru.otus.merets.library.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.merets.library.domain.Book;

@Repository
public interface BookReactiveRepository extends ReactiveMongoRepository<Book,String> {
}
