package ru.otus.merets.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.merets.library.domain.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
