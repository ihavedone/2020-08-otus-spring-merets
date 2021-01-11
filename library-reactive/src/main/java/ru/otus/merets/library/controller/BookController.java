package ru.otus.merets.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.merets.library.controller.dto.BookDto;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.repository.BookReactiveRepository;

import java.util.stream.Collectors;

/**
 * En: Several endpoints has some actions to try some WebFlux abilities (just
 * for studying);
 * Ru: Некоторые хэндлеры обогащены несколькими возможностями WebFlux (точнее возможностями
 * Flux и Mono), они могут не нести никакой практической пользы в данном случае,
 * используются в учебных целях
 */
@RestController
@AllArgsConstructor
public class BookController {
    private final BookReactiveRepository bookReactiveRepository;

    /**
     * En: 1 - Replace Leo Tolstoy to Artem
     *     2 - Add the first book to the response additionally
     *
     * Ru: 1 - Заменить Льва Толстого на Артема
     *     2 - Добавить дополнительно первую книгу в ответу
     */
    @GetMapping("/api/book")
    public Flux<Book> get() {
        return bookReactiveRepository
                .findAll()
                .map(b -> Book.builder()
                        .caption(b.getCaption())
                        .genres(b.getGenres())
                        .id(b.getId())
                        .authors(b.getAuthors().stream()
                                .map(a -> {
                                    if( a.getName().equals("Leo Tolstoy") ){
                                        return new Author("Artem");
                                    }
                                    return a;
                                })
                                .collect(Collectors.toSet())
                        )
                        .build())
                .mergeWith( bookReactiveRepository.findById("1") )
                ;
    }

    /**
     * En: Add empty object (in case of non-existing book)
     * Ru: Добавить болванку ответа для случая, когда нет книги с таким ID
     */
    @GetMapping("/api/book/{id}")
    public Mono<Book> get(@PathVariable("id") String id) {
        return bookReactiveRepository
                .findById(id)
                .defaultIfEmpty( Book.EMPTY );
    }

    @PostMapping("/api/book")
    public Mono<Book> save(@RequestBody BookDto bookDto) {
        return bookReactiveRepository.save(BookDto.fromDto(bookDto));
    }

    @PatchMapping("/api/book")
    public Mono<Book> update(@RequestBody BookDto bookDto) {
        if (bookDto.getId().isEmpty() || bookDto.getId() != null) {
            return bookReactiveRepository
                    .save(BookDto.fromDto(bookDto));
        }
        throw new NoBookException("An attempt to update broken book");
    }

    /**
     * En: In case of error returns HTTP status 400
     * Ru: В случае ошибок возвращаем код ответа 400
     */
    @DeleteMapping("/api/book/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return bookReactiveRepository
                .deleteById(id)
                .map( ResponseEntity::ok )
                .onErrorReturn( ResponseEntity.status(400).build() );
    }
}
