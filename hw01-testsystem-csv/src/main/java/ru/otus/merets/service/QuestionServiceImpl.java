package ru.otus.merets.service;

import ru.otus.merets.dao.QuestionDao;
import ru.otus.merets.domain.Question;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao){
        this.questionDao = questionDao;
    }

    @Override
    public Optional<Question> getQuestionById(String id) {
        return questionDao.getQuestionById(id);
    }

    @Override
    public Boolean checkAnswer(List<Integer> answers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }
}
