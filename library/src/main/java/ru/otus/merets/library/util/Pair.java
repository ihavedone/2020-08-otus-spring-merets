package ru.otus.merets.library.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pair<T1,T2> {
    private final T1 key;
    private final T2 value;
}
