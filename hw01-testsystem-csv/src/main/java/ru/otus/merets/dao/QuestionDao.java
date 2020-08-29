package ru.otus.merets.dao;

import ru.otus.merets.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {
    Optional<Question> getQuestionById(String id);
    List<Question> getAllQuestions();
}
