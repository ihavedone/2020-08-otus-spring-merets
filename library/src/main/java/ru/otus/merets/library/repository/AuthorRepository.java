package ru.otus.merets.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.merets.library.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
