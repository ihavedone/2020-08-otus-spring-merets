package ru.otus.merets.testsystem.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.merets.testsystem.config.ExamProperties;
import ru.otus.merets.testsystem.dao.CsvQuestionDao;
import ru.otus.merets.testsystem.domain.Answer;
import ru.otus.merets.testsystem.domain.Question;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@EnableConfigurationProperties(ExamProperties.class)
@DisplayName("TestingServiceImpl should ")
class TestingServiceImplTest {
    private static final String MESSAGE_PASSED = "Congratulations! You passed the exam.";
    private static final String CORRECT_ANSWER_ID = "1";

    @MockBean
    private QuestionService questionService;

    @MockBean
    private IOService iOService;

    @MockBean
    private L18nMessageService l18nMessageService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CsvQuestionDao csvQuestionDao;

    @Autowired
    private ExamProperties examProperties;

    @Autowired
    private TestingService testingService;

    @Test
    @DisplayName("calculate correct answers")
    void calculateCorrectAnswers() {
        Answer answer = new Answer(CORRECT_ANSWER_ID, "Answer #1", true);
        Question question = new Question("1", "Question #1", Arrays.asList(answer));

        given(l18nMessageService.getLocalizedMessage("messages.greeting")).willReturn(".");
        given(l18nMessageService.getLocalizedMessage("messages.passed")).willReturn(MESSAGE_PASSED);
        given(questionService.getAllQuestions()).willReturn(Arrays.asList(question));
        given(questionService.getCorrectAnswers(any())).willReturn( Arrays.asList(CORRECT_ANSWER_ID) );
        given(iOService.getString()).willReturn("1");

        testingService.startTest();

        ArgumentCaptor<Object> requestCaptor = ArgumentCaptor.forClass(Object.class);
        verify(iOService, times(5)).printMessage(requestCaptor.capture());

        assertThat( requestCaptor.getAllValues() ).contains(MESSAGE_PASSED);
    }
}