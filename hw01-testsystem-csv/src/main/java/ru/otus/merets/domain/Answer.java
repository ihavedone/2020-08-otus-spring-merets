package ru.otus.merets.domain;

public class Answer {
    private final String message;
    private final Boolean isRight;
    private final String id;

    public Answer(String id, String message, Boolean isRight){
        this.id = id;
        this.message = message;
        this.isRight = isRight;
    }

    @Override
    public String toString() {
        return " - " + id +
                " " + message +
                " (" + isRight +
                ") ";
    }
}
