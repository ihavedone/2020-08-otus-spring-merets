package ru.otus.merets.library.controller;

public class NoBookException extends RuntimeException {
    public NoBookException(String message) {
        super(message);
    }
}
