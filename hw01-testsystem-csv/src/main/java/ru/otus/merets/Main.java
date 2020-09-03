package ru.otus.merets;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.merets.service.TestingServiceImpl;
import ru.otus.merets.service.TestingService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestingService testingService = context.getBean(TestingServiceImpl.class);

        testingService.startTest();
    }
}
