package ru.otus.merets;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import ru.otus.merets.service.*;

@Configuration
@PropertySource("classpath:application.properties")
public class TestContextConfig {

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

    @Value("${exam.passScore}")
    Integer passScore;

    @Bean
    @Primary
    TestingService testingService(){
        return new TestingServiceImpl(questionService(),ioService(), passScore);
    }
}