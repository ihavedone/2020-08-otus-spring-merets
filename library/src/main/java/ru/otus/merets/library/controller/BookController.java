package ru.otus.merets.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.merets.library.controller.dto.BookDto;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.service.BookService;
import ru.otus.merets.library.service.NoBookException;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/api/book")
    public List<Book> get() {
        return bookService.getAll();
    }

    @GetMapping("/api/book/{id}")
    public Book get(@PathVariable("id") String id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/api/book")
    public Book save(@RequestBody BookDto bookDto) {
        return bookService.save( BookDto.fromDto(bookDto) );
    }

    /**
     * En: a "fat" controller. Yes, there is some logic in the endpoint, but it is ok for this case.
     * Ru: Не хочется накидывать логику (валидация, исключения) в контроллеры, но для сервиса это один и тот же метод.
     * Раздувать методы в сервисе не хочется еще больше.
     */
    @PatchMapping("/api/book")
    public Book update(@RequestBody BookDto bookDto) {
        if(bookDto.getId().isEmpty() || bookDto.getId()!=null) {
            return bookService.save(BookDto.fromDto(bookDto));
        }
        throw new NoBookException("An attempt to update broken book");
    }

    @DeleteMapping("/api/book/{id}")
    public void delete( @PathVariable("id") String id ){
        bookService.deleteById( id );
    }
}
