package ru.otus.merets.dao;

public class CsvQuestionDaoParsingException extends RuntimeException {
    public CsvQuestionDaoParsingException(String message) {
        super(message);
    }

    public CsvQuestionDaoParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
