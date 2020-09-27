package ru.otus.merets.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.merets.library.service.BookService;


@ShellComponent
public class ManagerService {
    private final BookService bookService;

    public ManagerService(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "add a new book", key = {"book-add"})
    public void addBook() {
        bookService.addNewBook();
    }

    @ShellMethod(value = "print all books", key = {"book-list"})
    public void getAllBooks() {
        bookService.printListOfBooks();
    }

    @ShellMethod(value = "print the book", key = {"book-get"})
    public void printBook() {
        bookService.printBook();
    }

    @ShellMethod(value = "delete the book by id", key = {"book-delete"})
    public void deleteBook() {
        bookService.deleteBook();
    }

    @ShellMethod(value="update the book", key = {"book-update"})
    public void updateBook(){
        bookService.updateBook();

    }

}
