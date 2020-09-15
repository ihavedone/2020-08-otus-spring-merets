package ru.otus.merets.testsystem.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.merets.testsystem.TestContextConfig;
import ru.otus.merets.testsystem.config.ExamProperties;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestContextConfig.class})
@DisplayName("TestingServiceImpl should ")
class CsvQuestionDaoTest {

    @Autowired
    private ExamProperties examProperties;

    @BeforeEach
    public void init(){
        given(examProperties.getLocale()).willReturn( Locale.forLanguageTag("en") );
    }

    @Test
    @DisplayName("parse csv file correctly")
    public void readCorrectCsv(){
        given(examProperties.getLocalizedPath()).willReturn("/questions/questions-correct_en.csv" );
        QuestionDao questionDao = new CsvQuestionDao(examProperties);
    }

    @Test
    @DisplayName("parse bad csv file with an exception")
    public void readIncorrectQuestions(){
        given(examProperties.getLocalizedPath()).willReturn("/questions/questions-incorrect-1_en.csv" );
        assertThrows(LoadingQuestionDaoException.class, () -> new CsvQuestionDao( examProperties));
        given(examProperties.getLocalizedPath()).willReturn("/questions/questions-incorrect-2_en.csv" );
        assertThrows(LoadingQuestionDaoException.class, () -> new CsvQuestionDao( examProperties));
    }

}