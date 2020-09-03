package ru.otus.merets.service;

import ru.otus.merets.domain.Question;

import java.util.List;

public class TestingServiceImpl implements TestingService {
    private final QuestionService questionService;
    private final PrintService printService;

    public TestingServiceImpl(QuestionService questionService, PrintService printService) {
        this.questionService = questionService;
        this.printService = printService;
    }

    @Override
    public void startTest() {
        printService.printMessage("We are staring...");

        List<Question> questionList = questionService.getAllQuestions();

        questionList.forEach( printService::printMessage);
    }

}
