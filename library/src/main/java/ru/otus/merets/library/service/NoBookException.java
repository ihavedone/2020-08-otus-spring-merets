package ru.otus.merets.library.service;

public class NoBookException extends RuntimeException {
    public NoBookException(String message) {
        super(message);
    }
}
