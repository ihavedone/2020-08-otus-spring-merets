package ru.otus.merets.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.merets.domain.Question;

import java.util.*;

@Service
public class TestingServiceImpl implements TestingService {
    private final QuestionService questionService;
    private final IOService iOService;

    private static final String MESSAGE_PASSED = "Congratulations! You passed the exam.";
    private static final String MESSAGE_FAILED = "We are so sorry... You failed the exam. Try one more time";
    private static final String MESSAGE_ASK_NAME = "Please enter your name and press Enter: ";
    private static final String MESSAGE_BANNER = "After each question you should enter your answers " +
            "(one or several). In case of many correct answers, please use space like a separator, e.g. '1 2 3'";
    private static final String MESSAGE_RESULT = "%s, your score is %d from %d (necessary at least %d)";

    @Value("${exam.passScore}")
    private Integer passScore = 3;

    public TestingServiceImpl(QuestionService questionService, IOService iOService) {
        this.questionService = questionService;
        this.iOService = iOService;
    }

    public String getName() {
        iOService.printMessage(MESSAGE_ASK_NAME);
        return iOService.getString();
    }

    @Override
    public void startTest() {
        String name = getName();
        long rank;

        iOService.printMessage(MESSAGE_BANNER);

        List<Question> questionList = questionService.getAllQuestions();
        rank = questionList.stream().filter(this::ask).count();

        iOService.printMessage(String.format(MESSAGE_RESULT, name, rank, questionList.size(), passScore));
        if (rank >= passScore) {
            iOService.printMessage(MESSAGE_PASSED);
        } else {
            iOService.printMessage(MESSAGE_FAILED);
        }
    }

    @Override
    public boolean ask(Question question) {
        iOService.printMessage(question);
        String answer = iOService.getString();
        List<String> correctAnswers = questionService.getCorrectAnswers(question);
        List<String> usersAnswers = Arrays.asList(answer.split(" "));

        if (new HashSet<>(correctAnswers).equals(new HashSet<>(usersAnswers))) {
            return true;
        }
        return false;
    }
}
