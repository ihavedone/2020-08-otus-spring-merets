package ru.otus.merets.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.repository.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById( id )
                .orElseThrow( () -> new NoBookException(String.format("There is not a book with id %s", id) ) );
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
