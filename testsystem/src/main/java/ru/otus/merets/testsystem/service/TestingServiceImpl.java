package ru.otus.merets.testsystem.service;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import ru.otus.merets.testsystem.config.ExamProperties;
import ru.otus.merets.testsystem.domain.Question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@ShellComponent
public class TestingServiceImpl implements TestingService {
    private final QuestionService questionService;
    private final IOService iOService;
    private final ExamProperties examProperties;
    private final L18nMessageService l18nMessageService;

    public TestingServiceImpl(QuestionService questionService, IOService iOService,
                              ExamProperties examProperties, L18nMessageService l18nMessageService) {
        this.questionService = questionService;
        this.iOService = iOService;
        this.examProperties = examProperties;
        this.l18nMessageService = l18nMessageService;
        this.l18nMessageService.setDefaultLocale(examProperties.getLocale());
    }

    private String getName() {
        iOService.printMessage(l18nMessageService.getLocalizedMessage("messages.ask_name"));
        return iOService.getString();
    }

    @Override
    @ShellMethod(value = "Start the test", key = {"go", "start"})
    public void startTest() {
        String name = getName();
        long rank;
        iOService.printMessage(l18nMessageService.getLocalizedMessage("messages.greeting"));

        List<Question> questionList = questionService.getAllQuestions();
        rank = questionList.stream().filter(this::ask).count();

        iOService.printMessage(
                l18nMessageService.getLocalizedMessage("messages.result",
                        name,
                        String.valueOf(rank),
                        String.valueOf(questionList.size()),
                        examProperties.getScore().toString()));

        if (rank >= examProperties.getScore()) {
            iOService.printMessage(l18nMessageService.getLocalizedMessage("messages.passed"));
        } else {
            iOService.printMessage(l18nMessageService.getLocalizedMessage("messages.failed"));
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
