package ru.otus.merets.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Genre;
import ru.otus.merets.library.repository.AuthorRepository;
import ru.otus.merets.library.repository.BookRepository;
import ru.otus.merets.library.repository.GenreRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final IOService ioService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private Set<Author> getAuthorsViaUI() {
        authorRepository.findAll().forEach(ioService::printMessage);
        ioService.printMessage("Enter authors ids:");
        return new HashSet<>(authorRepository.findAllById(
                Arrays.stream(ioService.getString().split(" "))
                        .map(Long::valueOf)
                        .collect(Collectors.toList())));
    }

    private Set<Genre> getGenresViaUI() {
        genreRepository.findAll().forEach(ioService::printMessage);
        ioService.printMessage("Enter genres' ids:");
        return new HashSet<>(genreRepository.findAllById(
                Arrays.stream(ioService.getString().split(" "))
                        .map(Long::valueOf)
                        .collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public void add() {
        ioService.printMessage("Enter caption:");
        String caption = ioService.getString();

        Book book = new Book(0L, caption, getAuthorsViaUI(), getGenresViaUI());
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void delete() {
        bookRepository.delete(getViaUI());
    }

    @Transactional
    @Override
    public void update() {
        Book book = getViaUI();

        ioService.printMessage(String.format("Enter new caption (old caption is '%s'): ", book.getCaption()));
        book.setCaption(ioService.getString());
        book.setAuthors(getAuthorsViaUI());
        book.setGenres(getGenresViaUI());

        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public void printAll() {
        ioService.printMessage(
                bookRepository.findAll()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public void print() {
        ioService.printMessage(getViaUI());
    }

    @Override
    public Book getViaUI() {
        ioService.printMessage("Enter book's id: ");
        String value = ioService.getString();
        return bookRepository.findById(
                Long.parseLong(value))
                .orElseThrow(() -> new NoBookException(String.format("There is not a book with id %s", value)));
    }
}
