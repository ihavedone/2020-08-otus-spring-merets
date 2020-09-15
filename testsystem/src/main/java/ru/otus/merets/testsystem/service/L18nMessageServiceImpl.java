package ru.otus.merets.testsystem.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class L18nMessageServiceImpl implements L18nMessageService {

    private final MessageSource messageSource;
    private Locale locale;

    public L18nMessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = Locale.ENGLISH;
    }

    @Override
    public String getLocalizedMessage(String label, String... params) {
        return messageSource.getMessage(label, params.length == 0 ? null : params, locale);
    }

    @Override
    public void setDefaultLocale(Locale locale) {
        this.locale = locale;
    }
}
