package ru.otus.merets.testsystem.service;


import org.springframework.stereotype.Service;
import ru.otus.merets.testsystem.dao.QuestionDao;
import ru.otus.merets.testsystem.domain.Answer;
import ru.otus.merets.testsystem.domain.Question;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    @Override
    public List<String> getCorrectAnswers(Question question) {
        return question.getAnswers()
                .stream()
                .filter(Answer::isCorrect)
                .map(Answer::getId)
                .collect(Collectors.toList());
    }
}
