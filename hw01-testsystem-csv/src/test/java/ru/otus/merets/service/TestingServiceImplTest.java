package ru.otus.merets.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.merets.TestContextConfig;
import ru.otus.merets.domain.Answer;
import ru.otus.merets.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestContextConfig.class})
@DisplayName("TestingServiceImpl should ")
class TestingServiceImplTest {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private IOService iOService;

    @Autowired
    TestingService testingService;

    @Test
    @DisplayName("calculate correct answers")
    void askTest() {
        Answer answer = new Answer("1","Answer #1",true);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer);

        Question question = new Question("1","Question #1", answers);

        List<String> correctAnswers = new ArrayList<>();
        correctAnswers.add("1");

        given(questionService.getCorrectAnswers(any())).willReturn(correctAnswers);
        given(iOService.getString()).willReturn("1");

        testingService.ask(question);
        Assertions.assertEquals(1, testingService.getRank() );
    }
}