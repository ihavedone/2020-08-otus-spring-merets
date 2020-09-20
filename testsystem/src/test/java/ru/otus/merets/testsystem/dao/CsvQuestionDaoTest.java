package ru.otus.merets.testsystem.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import ru.otus.merets.testsystem.config.ExamProperties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("CsvQuestionDao should ")
class CsvQuestionDaoTest {

    @Configuration
    static class NestedContext{
    }

    @MockBean
    private ExamProperties examProperties;

    @Test
    @DisplayName("load correct csv file without any errors")
    public void loadCorrectCsv(){
        given(examProperties.getLocalizedPath()).willReturn("/questions/questions-correct_en.csv" );
        QuestionDao questionDao = new CsvQuestionDao(examProperties);
    }

    @Test
    @DisplayName("load bad csv with an insufficient quantity of columns with an exception")
    public void loadBadCsvWithInsufficientQuantityOfColumns(){
        given(examProperties.getLocalizedPath()).willReturn("/questions/questions-incorrect-1_en.csv" );
        assertThrows(LoadingQuestionDaoException.class, () -> new CsvQuestionDao( examProperties));
    }

    @Test
    @DisplayName("load bad csv with a large quantity of columns with an exception")
    public void loadBadCsvWithLargeAmountOfColumns(){
        given(examProperties.getLocalizedPath()).willReturn("/questions/questions-incorrect-2_en.csv" );
        assertThrows(LoadingQuestionDaoException.class, () -> new CsvQuestionDao( examProperties));
    }

}