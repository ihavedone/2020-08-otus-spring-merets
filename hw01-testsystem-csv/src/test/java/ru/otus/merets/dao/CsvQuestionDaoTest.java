package ru.otus.merets.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CsvQuestionDao should ")
class CsvQuestionDaoTest {

    @Test
    @DisplayName("parse csv file correctly")
    public void readCorrectCsv(){
        QuestionDao questionDao = new CsvQuestionDao("/questions-correct.csv");
    }

    @Test
    @DisplayName("parse bad csv file with an exception")
    public void readIncorrectQuestions(){
        assertThrows(CsvQuestionDaoParsingException.class, () -> new CsvQuestionDao("/questions-incorrect-1.csv"));
        assertThrows(CsvQuestionDaoParsingException.class, () -> new CsvQuestionDao("/questions-incorrect-2.csv"));
    }

}