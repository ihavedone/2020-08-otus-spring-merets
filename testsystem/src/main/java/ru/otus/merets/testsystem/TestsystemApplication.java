package ru.otus.merets.testsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.merets.testsystem.config.ExamProperties;
import ru.otus.merets.testsystem.service.TestingService;
import ru.otus.merets.testsystem.service.TestingServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties(ExamProperties.class)
public class TestsystemApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(TestsystemApplication.class, args);
        TestingService testingService = context.getBean(TestingServiceImpl.class);
        testingService.startTest();
    }
}
