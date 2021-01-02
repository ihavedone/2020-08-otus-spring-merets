package ru.otus.merets.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.service.BookService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/api/book")
    public List<Book> get(){
        return bookService.getAll();
    }

    @GetMapping("/api/book/{id}")
    public Book get(@PathVariable("id") String id){
        return bookService.getBookById(id);
    }
}
