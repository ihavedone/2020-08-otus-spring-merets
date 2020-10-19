package ru.otus.merets.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.merets.library.service.BookService;
import ru.otus.merets.library.service.CommentService;


@ShellComponent
@AllArgsConstructor
public class ManagerService {
    private final BookService bookService;
    private final CommentService commentService;


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

    @ShellMethod(value="comment the book", key = {"comment-add"})
    public void commentBook(){
        commentService.addComment();
    }

    @ShellMethod(value="delete comment", key = {"comment-delete"})
    public void deleteComment(){
        commentService.deleteComment();
    }

    @ShellMethod(value="show comment", key = {"comment-get"})
    public void showComment(){
        commentService.showComment();
    }

}
