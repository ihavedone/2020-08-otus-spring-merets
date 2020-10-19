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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final IOService ioService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private Set<Author> getAuthorsViaIds() {
        authorRepository.findAll().forEach(ioService::printMessage);
        ioService.printMessage("Enter authors ids:");
        return authorRepository.findByIds(
                Arrays.stream(ioService.getString().split(" "))
                        .map(Long::valueOf)
                        .collect(Collectors.toList()));
    }

    private Set<Genre> getGenresViaIds() {
        genreRepository.findAll().forEach(ioService::printMessage);
        ioService.printMessage("Enter genres' ids:");
        return genreRepository.findByIds(
                Arrays.stream(ioService.getString().split(" "))
                        .map(Long::valueOf)
                        .collect(Collectors.toList()));
    }

    @Transactional
    @Override
    public void addNewBook() {
        ioService.printMessage("Enter caption:");
        String caption = ioService.getString();

        Book book = new Book(0L, caption, getAuthorsViaIds(), getGenresViaIds());
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBook() {
        bookRepository.delete(select());
    }

    @Transactional
    @Override
    public void updateBook() {
        Book book = select();

        ioService.printMessage("Enter new caption: ");
        book.setCaption( ioService.getString() );

        book.setAuthors(getAuthorsViaIds());
        book.setGenres(getGenresViaIds());

        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public void printListOfBooks() {
        ioService.printMessage(
                bookRepository.findAll()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public void printBook() {
        ioService.printMessage(select());
    }

    @Override
    public Book select() {
        ioService.printMessage("Enter book's id: ");
        String value = ioService.getString();
        return bookRepository.findById(
                Long.parseLong(value))
                .orElseThrow(() -> new NoBookException(String.format("There is not a book with id %s", value)));
    }
}
