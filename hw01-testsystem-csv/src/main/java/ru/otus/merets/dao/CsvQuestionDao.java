package ru.otus.merets.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.merets.domain.Answer;
import ru.otus.merets.domain.Question;

import java.io.*;
import java.util.*;

public class CsvQuestionDao implements QuestionDao {
    private final List<Question> questions;

    public CsvQuestionDao(String resource) {
        questions = new ArrayList<>();
        parseCsv(resource);
    }

    public Optional<Question> getQuestionById(String id) {
        return questions
                .stream()
                .filter(q -> q.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Question> getAllQuestions() {
        return questions;
    }

    private void parseCsv(String resource) {
        InputStream inputStream = getClass().getResourceAsStream(resource);
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
