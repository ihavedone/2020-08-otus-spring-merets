package ru.otus.merets.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.merets.domain.Answer;
import ru.otus.merets.domain.Question;

import java.io.*;
import java.util.*;

public class CsvQuestionDao implements QuestionDao {
    private List<Question> questions;

    public CsvQuestionDao(String resource ) {
        questions = new ArrayList<>();
        BufferedReader bufferedReader = loadFile(resource);
        parseCsv(bufferedReader);
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

    private BufferedReader loadFile( String resource ){
        InputStream inputStream = getClass().getResourceAsStream(resource);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    private void parseCsv(BufferedReader reader){
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.RFC4180.parse(reader);
        } catch (Exception e) {
            throw new CsvQuestionDaoIncorrectFileException("Incorrect CSV file", e);
        }

        for (CSVRecord record : records) {
            if(record.size()<5){
                throw new CsvQuestionDaoIncorrectFileException("CSV file sould contain more than 4 columns");
            }
            if( (record.size()-2)%3 != 0 ){
                throw new CsvQuestionDaoIncorrectFileException("Each of answers should contain exactly 3 columns");
            }
            List<Answer> answers = new ArrayList<>();
            for( int i=2; i< record.size(); i+=3 ) {
                answers.add( new Answer(record.get(i), record.get(i+1), Boolean.valueOf( record.get(i+2)) ) );
            }
            questions.add( new Question(record.get(0), record.get(1), answers) );
        }
    }

}
