package ru.otus.merets.testsystem.service;

import java.util.Locale;

public interface L18nMessageService {
    String getLocalizedMessage(String label, String...params);
    void setDefaultLocale(Locale locale);
}
