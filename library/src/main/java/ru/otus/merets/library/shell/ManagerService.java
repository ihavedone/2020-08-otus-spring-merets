package ru.otus.merets.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.merets.library.service.BookService;
import ru.otus.merets.library.service.CommentService;
import ru.otus.merets.library.service.IOService;


@ShellComponent
@AllArgsConstructor
public class ManagerService {
    private final BookService bookService;
    private final CommentService commentService;
    private final IOService ioService;

    @ShellMethod(value = "add a new book", key = {"book-add"})
    public void addBook() {
        bookService.add();
    }

    @ShellMethod(value = "print all books", key = {"book-list"})
    public void getAllBooks() {
        bookService.printAll();
    }

    @ShellMethod(value = "print the book", key = {"book-get"})
    public void printBook() {
        bookService.print();
    }

    @ShellMethod(value = "delete the book by id", key = {"book-delete"})
    public void deleteBook() {
        bookService.delete();
    }

    @ShellMethod(value="update the book", key = {"book-update"})
    public void updateBook(){
        bookService.update();
    }

    @ShellMethod(value="comment the book", key = {"comment-add"})
    public void commentBook(){
        commentService.add();
    }

    @ShellMethod(value="delete comment", key = {"comment-delete"})
    public void deleteComment(){
        commentService.delete();
    }

    @ShellMethod(value="show comment", key = {"comment-get"})
    public void showComment(){
        commentService.print();
    }

    @ShellMethod(value="help", key = {"?"})
    public void printHelp(){
        ioService.printMessage("######### MENU ###########");
        ioService.printMessage("1. Book ");
        ioService.printMessage("   book-list = print the list of books");
        ioService.printMessage("   book-add = add a new book");
        ioService.printMessage("   book-get = print a book with certain identifier");
        ioService.printMessage("   book-update = change the book");
        ioService.printMessage("   book-delete = delete the book");
        ioService.printMessage("2. Comment ");
        ioService.printMessage("   comment-add = add a new comment to the book");
        ioService.printMessage("   comment-get = print a comment with certain identifier");
        ioService.printMessage("   comment-delete = delete the comment");
        ioService.printMessage("##########################");
    }

}
