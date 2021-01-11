package ru.otus.merets.library.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.merets.library.domain.Author;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.domain.Genre;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private String id;
    private String caption;
    private String authors;
    private String genres;

    public static Book fromDto(BookDto bookDto){
        return Book.builder()
                .caption(bookDto.getCaption())
                .id( (bookDto.getId()==null||bookDto.getId().isEmpty())?null: bookDto.getId() )
                .authors(
                        Arrays.stream(bookDto.getAuthors().split(","))
                                .map(String::trim)
                                .map(Author::new)
                                .collect(Collectors.toSet())
                )
                .genres(
                        Arrays.stream(bookDto.getGenres().split(","))
                                .map(String::trim)
                                .map(Genre::new)
                                .collect(Collectors.toSet())
                ).build();
    }
}
