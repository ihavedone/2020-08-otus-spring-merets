package ru.otus.merets.library.repository;

import ru.otus.merets.library.domain.Genre;

import java.util.List;
import java.util.Set;

public interface GenreRepository {
    Set<Genre> findAll();
    Set<Genre> findByIds(List<Long> ids);
}
