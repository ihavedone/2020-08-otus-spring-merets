package ru.otus.merets.service;

import ru.otus.merets.dao.QuestionDao;
import ru.otus.merets.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao){
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }
}
