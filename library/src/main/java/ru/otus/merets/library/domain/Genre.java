package ru.otus.merets.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Genre {
    private Long id;
    private final String name;
}
