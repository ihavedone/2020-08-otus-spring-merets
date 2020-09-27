package ru.otus.merets.library.dao;


import ru.otus.merets.library.domain.Genre;

import java.util.List;
import java.util.Set;

public interface GenreDao {
    Set<Genre> getAll();
    Set<Genre> getByIds(List<Long> ids);
}
