package ru.otus.merets.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Author {
    private Long id;
    private final String name;
}
