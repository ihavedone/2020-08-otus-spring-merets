package ru.otus.merets.testsystem.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.merets.testsystem.service.TestingService;

@ShellComponent
public class SpringShellController {
    private final TestingService testingService;

    public SpringShellController(TestingService testingService) {
        this.testingService = testingService;
    }

    @ShellMethod(value = "Start the test", key = {"go", "start"})
    public void start(){
        testingService.startTest();
    }
}
