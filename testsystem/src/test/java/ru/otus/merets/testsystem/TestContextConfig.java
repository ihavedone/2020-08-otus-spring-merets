package ru.otus.merets.testsystem;


import org.mockito.Mockito;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.otus.merets.testsystem.config.ExamProperties;
import ru.otus.merets.testsystem.config.Localization;
import ru.otus.merets.testsystem.service.*;

@Configuration
@EnableConfigurationProperties(ExamProperties.class)
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

    @Bean
    @Primary
    MessageSource messageSource(){
        Localization localization = new Localization();
        return localization.messageSource();
    }

    @Bean
    @Primary
    TestingService testingService(){
        return new TestingServiceImpl(questionService(),ioService(), messageSource(), examProperties());
    }
}