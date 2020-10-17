package ru.otus.merets.library.repository;

import ru.otus.merets.library.domain.Author;

import java.util.List;
import java.util.Set;

public interface AuthorRepository {
    Set<Author> findAll();

    Set<Author> findByIds(List<Long> ids);
}
