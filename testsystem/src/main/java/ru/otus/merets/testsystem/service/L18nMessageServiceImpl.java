package ru.otus.merets.testsystem.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.merets.testsystem.config.ExamProperties;

@Service
public class L18nMessageServiceImpl implements L18nMessageService {

    private final MessageSource messageSource;
    private final ExamProperties examProperties;

    public L18nMessageServiceImpl(MessageSource messageSource, ExamProperties examProperties) {
        this.messageSource = messageSource;
        this.examProperties = examProperties;
    }

    @Override
    public String getLocalizedMessage(String label, String... params) {
        return messageSource.getMessage(label, params.length == 0 ? null : params, examProperties.getLocale());
    }
}
