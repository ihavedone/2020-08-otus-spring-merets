package ru.otus.merets.testsystem;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.otus.merets.testsystem.config.ExamProperties;
import ru.otus.merets.testsystem.service.*;

import static org.mockito.BDDMockito.given;

@Configuration
public class TestContextConfig {
    @Bean
    @Primary
    ExamProperties examProperties(){
        return Mockito.mock(ExamProperties.class);
    }

    @Bean
    @Primary
    QuestionService questionService(){
        return Mockito.mock(QuestionServiceImpl.class);
    }

    @Bean
    @Primary
    IOService ioService(){
        return Mockito.mock(IOServiceConsole.class);
    }

    @Autowired
    MessageSource messageSource;

    @Autowired
    L18nMessageService l18nMessageService(){
        var service = Mockito.mock(L18nMessageServiceImpl.class);
        given(service.getLocalizedMessage("messages.greeting")).willReturn("." );
        given(service.getLocalizedMessage("messages.passed")).willReturn("Congratulations! You passed the exam." );

        return service;
    }

    @Bean
    @Primary
    TestingServiceImpl testingService(){
        return new TestingServiceImpl(questionService(),ioService(), examProperties(),l18nMessageService());
    }
}