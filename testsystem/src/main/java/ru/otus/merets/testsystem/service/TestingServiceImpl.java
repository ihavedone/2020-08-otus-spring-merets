package ru.otus.merets.testsystem.service;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.merets.testsystem.config.ExamProperties;
import ru.otus.merets.testsystem.domain.Question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private final QuestionService questionService;
    private final IOService iOService;
    private final MessageSource messageSource;
    private final ExamProperties examProperties;

    public TestingServiceImpl(QuestionService questionService, IOService iOService, MessageSource messageSource, ExamProperties examProperties) {
        this.questionService = questionService;
        this.iOService = iOService;
        this.messageSource = messageSource;
        this.examProperties = examProperties;
    }

    private String getName() {
        iOService.printMessage(getLocalizedMessage("messages.ask_name", null));
        return iOService.getString();
    }

    private String getLocalizedMessage(String label, Object[] params) {
        return messageSource.getMessage(label, params, examProperties.getLocale());
    }

    @Override
    public void startTest() {
        String name = getName();
        long rank;
        iOService.printMessage(getLocalizedMessage("messages.greeting", null));

        List<Question> questionList = questionService.getAllQuestions();
        rank = questionList.stream().filter(this::ask).count();

        iOService.printMessage(
                getLocalizedMessage("messages.result",
                        new String[]{name,
                                String.valueOf(rank),
                                String.valueOf(questionList.size()),
                                examProperties.getScore().toString()}));

        if (rank >= examProperties.getScore()) {
            iOService.printMessage(getLocalizedMessage("messages.passed", null));
        } else {
            iOService.printMessage(getLocalizedMessage("messages.failed", null));
        }
    }

    private boolean ask(Question question) {
        iOService.printMessage(question);
        String answer = iOService.getString();
        List<String> correctAnswers = questionService.getCorrectAnswers(question);
        List<String> usersAnswers = Arrays.asList(answer.split(" "));

        return new HashSet<>(correctAnswers).equals(new HashSet<>(usersAnswers));
    }
}
