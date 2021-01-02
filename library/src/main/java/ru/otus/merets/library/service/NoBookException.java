package ru.otus.merets.library.service;

import java.util.function.Supplier;

public class NoBookException extends RuntimeException {
    public NoBookException(String message) {
        super(message);
    }

    public NoBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
