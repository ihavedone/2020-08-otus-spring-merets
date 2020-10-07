package ru.otus.merets.library.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pair<T1,T2> {
    private T1 key;
    private T2 value;
}
