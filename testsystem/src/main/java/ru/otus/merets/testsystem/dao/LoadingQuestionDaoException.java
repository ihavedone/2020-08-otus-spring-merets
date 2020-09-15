package ru.otus.merets.testsystem.dao;

public class LoadingQuestionDaoException extends RuntimeException {
    public LoadingQuestionDaoException(String message) {
        super(message);
    }

    public LoadingQuestionDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
