package ru.otus.merets.service;

import ru.otus.merets.domain.Question;

public interface TestingService {
    void startTest();
    boolean ask(Question question);
}
