package ru.otus.merets.domain;

import ru.otus.merets.dao.QuestionDao;

import java.util.List;

public class Question {
    private final List<Answer> answers;
    private final String message;
    private final String id;

    public Question(String id, String message, List<Answer> answers){
        this.id = id;
        this.message = message;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "# "+ id +
                ". " + message );
        answers.stream().forEach( a -> stringBuilder.append("\n"+a.toString()));
        return stringBuilder.toString();
    }
}
