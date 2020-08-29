package ru.otus.merets.service;

import ru.otus.merets.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Optional<Question> getQuestionById(String id);
    Boolean checkAnswer( List<Integer> answers);
    List<Question> getAllQuestions();
}
