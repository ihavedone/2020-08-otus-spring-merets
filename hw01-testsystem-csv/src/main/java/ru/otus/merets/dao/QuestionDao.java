package ru.otus.merets.dao;

import ru.otus.merets.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions();
}
