package ru.otus.merets.testsystem.dao;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;
import ru.otus.merets.testsystem.config.ExamProperties;
import ru.otus.merets.testsystem.domain.Answer;
import ru.otus.merets.testsystem.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CsvQuestionDao implements QuestionDao {
    private final List<Question> questions;
    private final ExamProperties examProperties;

    public CsvQuestionDao(ExamProperties examProperties) {
        this.examProperties = examProperties;
        questions = new ArrayList<>();
        parseCsv(examProperties.getPath());
    }

    @Override
    public List<Question> getAllQuestions() {
        return questions;
    }

    private void parseCsv(String resource) {
        InputStream inputStream = getClass().getResourceAsStream("/questions/".concat(examProperties.getLocale().toString()).concat("/".concat(resource)));
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(reader);
            for (CSVRecord record : records) {
                if (record.size() < 5) {
                    throw new CsvQuestionDaoParsingException("CSV file should contain more than 4 columns");
                }
                if ((record.size() - 2) % 3 != 0) {
                    throw new CsvQuestionDaoParsingException("Each of answers should contain exactly 3 columns");
                }
                List<Answer> answers = new ArrayList<>();
                for (int i = 2; i < record.size(); i += 3) {
                    answers.add(new Answer(record.get(i), record.get(i + 1), Boolean.valueOf(record.get(i + 2))));
                }
                questions.add(new Question(record.get(0), record.get(1), answers));
            }
        } catch (Exception e) {
            throw new CsvQuestionDaoParsingException("Incorrect CSV file", e);
        }
    }
}
