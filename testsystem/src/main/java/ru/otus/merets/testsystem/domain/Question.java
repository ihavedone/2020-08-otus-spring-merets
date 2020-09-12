package ru.otus.merets.testsystem.domain;

import java.util.List;

public class Question {
    private final List<Answer> answers;
    private final String message;
    private final String id;

    public Question(String id, String message, List<Answer> answers) {
        this.id = id;
        this.message = message;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ").append(id).append(". ").append(message);
        answers.forEach(a -> stringBuilder.append("\n").append(a.toString()));
        return stringBuilder.toString();
    }
}
