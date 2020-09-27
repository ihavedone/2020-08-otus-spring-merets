package ru.otus.merets.library.dao;

import ru.otus.merets.library.domain.Author;

import java.util.List;
import java.util.Set;

public interface AuthorDao {
    Set<Author> getAll();
    Set<Author> getByIds(List<Long> ids);
}
