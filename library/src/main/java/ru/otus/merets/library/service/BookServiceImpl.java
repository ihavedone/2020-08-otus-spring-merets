package ru.otus.merets.library.service;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Genre;
import ru.otus.merets.library.repository.AuthorRepository;
import ru.otus.merets.library.repository.BookRepository;
import ru.otus.merets.library.repository.GenreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final IOService ioService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById( id )
                .orElseThrow( () -> new NoBookException(String.format("There is not a book with id %s", id) ) );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    private Set<Author> getAuthorsViaUI() {
        authorRepository.findAll().forEach(ioService::printMessage);
        ioService.printMessage("Enter authors ids:");
        return Sets.newHashSet(authorRepository.findAllById(
                Arrays.stream(ioService.getString().split(" "))
                        .map(String::valueOf)
                        .collect(Collectors.toList())));
    }

    private Set<Genre> getGenresViaUI() {
        genreRepository.findAll().forEach(ioService::printMessage);
        ioService.printMessage("Enter genres' ids:");
        return Sets.newHashSet(genreRepository.findAllById(
                Arrays.stream(ioService.getString().split(" "))
                        .map(String::valueOf)
                        .collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }


}
