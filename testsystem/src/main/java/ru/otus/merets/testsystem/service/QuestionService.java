package ru.otus.merets.testsystem.service;


import ru.otus.merets.testsystem.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();

    List<String> getCorrectAnswers(Question question);
}
