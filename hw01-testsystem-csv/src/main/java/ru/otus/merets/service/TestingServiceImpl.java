package ru.otus.merets.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.merets.domain.Question;

import java.util.*;

@Service
public class TestingServiceImpl implements TestingService {
    private final QuestionService questionService;
    private final IOService iOService;
    private String name;
    private Integer rank = 0;

    @Value("${message.passed}")
    private String MESSAGE_PASSED;

    @Value("${message.failed}")
    private String MESSAGE_FAILED;

    @Value("${message.askName}")
    private String MESSAGE_ASK_NAME;

    @Value("${message.banner}")
    private String MESSAGE_BANNER;

    @Value("${message.result}")
    private String MESSAGE_RESULT;

    @Value("${exam.passScore}")
    private Integer passScore;

    public TestingServiceImpl(QuestionService questionService, IOService iOService) {
        this.questionService = questionService;
        this.iOService = iOService;
    }

    public void getName() {
        iOService.printMessage(MESSAGE_ASK_NAME);
        name = iOService.getString();
    }

    @Override
    public void startTest() {
        getName();
        iOService.printMessage(MESSAGE_BANNER);

        List<Question> questionList = questionService.getAllQuestions();
        questionList.forEach(this::ask);

        iOService.printMessage(String.format(MESSAGE_RESULT, name, rank, questionList.size(), passScore));
        if (rank >= passScore) {
            iOService.printMessage(MESSAGE_PASSED);
        } else {
            iOService.printMessage(MESSAGE_FAILED);
        }
    }

    @Override
    public void ask(Question question) {
        iOService.printMessage(question);
        String answer = iOService.getString();
        List<String> correctAnswers = questionService.getCorrectAnswers(question);
        List<String> usersAnswers = Arrays.asList(answer.split(" "));

        if (new HashSet<>(correctAnswers).equals(new HashSet<>(usersAnswers))) {
            rank++;
        }
    }

    @Override
    public Integer getRank(){
        return rank;
    }
}
