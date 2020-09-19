package ru.otus.merets.testsystem.dao;

import ru.otus.merets.testsystem.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions();
}
