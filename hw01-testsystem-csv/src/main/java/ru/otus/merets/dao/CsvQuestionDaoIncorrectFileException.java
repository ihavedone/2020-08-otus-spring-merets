package ru.otus.merets.dao;

public class CsvQuestionDaoIncorrectFileException extends RuntimeException {
    public CsvQuestionDaoIncorrectFileException(String message) {
        super(message);
    }

    public CsvQuestionDaoIncorrectFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
