package ru.otus.merets.testsystem.domain;

public class Answer {
    private final String message;
    private final Boolean isRight;
    private final String id;

    public Answer(String id, String message, Boolean isRight) {
        this.id = id;
        this.message = message;
        this.isRight = isRight;
    }

    public Boolean isCorrect() {
        return isRight;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return " - " + id +
                " " + message +
                " (" + isRight +
                ") ";
    }
}
