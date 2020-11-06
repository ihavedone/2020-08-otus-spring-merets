package ru.otus.merets.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.merets.library.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre,String> {
}
