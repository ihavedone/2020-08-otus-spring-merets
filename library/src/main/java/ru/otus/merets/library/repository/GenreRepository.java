package ru.otus.merets.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.merets.library.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
