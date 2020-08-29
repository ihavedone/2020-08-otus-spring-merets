package ru.otus.merets.service;

import org.w3c.dom.ls.LSOutput;
import ru.otus.merets.domain.Question;

import java.util.List;

public class ConsoleTestingService implements TestingService {
    private final QuestionService questionService;

    public ConsoleTestingService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void startTest() {
        System.out.println("Test is starting...");

        List<Question> questionList = questionService.getAllQuestions();

        questionList
                .stream()
                .forEach(this::showQuestion);
    }

    @Override
    public void showQuestion(Question question) {
        System.out.println(question);
    }

}
