package ru.otus.merets.library.service;

import org.springframework.stereotype.Service;
import ru.otus.merets.library.dao.AuthorDao;
import ru.otus.merets.library.dao.BookDao;
import ru.otus.merets.library.dao.GenreDao;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Genre;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final IOService ioService;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookServiceImpl(IOService ioService, BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.ioService = ioService;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    private Set<Author> getAuthorsViaIds(){
        authorDao.getAll().forEach(ioService::printMessage);
        ioService.printMessage("Enter authors ids:");
        return authorDao.getByIds(
                Arrays.stream(ioService.getString().split(" "))
                        .map(Long::valueOf)
                        .collect(Collectors.toList()));
    }

    private Set<Genre> getGenresViaIds(){
        genreDao.getAll().forEach(ioService::printMessage);
        ioService.printMessage("Enter genres' ids:");
        return genreDao.getByIds(
                Arrays.stream(ioService.getString().split(" "))
                        .map(Long::valueOf)
                        .collect(Collectors.toList()));
    }

    @Override
    public void addNewBook() {
        ioService.printMessage("Enter caption:");
        String caption = ioService.getString();

        Book book = new Book(null, caption, getAuthorsViaIds(), getGenresViaIds());
        bookDao.insert(book);
    }

    @Override
    public void deleteBook() {
        ioService.printMessage("Enter id: ");
        bookDao.delete( Long.parseLong( ioService.getString() ) );
    }

    @Override
    public void updateBook() {
        ioService.printMessage("List of current books: ");
        ioService.printMessage( bookDao.getAll() );
        ioService.printMessage("Enter an ID of target book: ");
        Long bookId = Long.valueOf(ioService.getString());
        ioService.printMessage("Enter new caption: ");
        String bookCaption = ioService.getString();

        Book book = new Book(bookId,bookCaption, getAuthorsViaIds(), getGenresViaIds());
        bookDao.update(book);
    }

    @Override
    public void printListOfBooks() {
        ioService.printMessage(
                bookDao.getAll()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "))
        );
    }

    @Override
    public void printBook() {
        ioService.printMessage("Enter book's id: ");
        ioService.printMessage(
                bookDao.getById(Long.parseLong( ioService.getString()))
        );
    }
}
