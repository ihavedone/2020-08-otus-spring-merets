package ru.otus.merets.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.merets.library.domain.Book;

/**
 * En: Non-reactive repository for Mongo to do migration (Mongock doesn't work with reactive driver)
 * Ru: Нереактивный репозиторий для миграции, Монгок не работает с реактивными
 * https://www.mongock.io/reactive
 * Although support to reactive streams and repositories is in the roadmap and will be supported by Mongock, it's not yet.
 */
@Repository
public interface BookRepository extends MongoRepository<Book,String> {
}
