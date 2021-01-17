package ru.otus.merets.library;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories(basePackages = "ru.otus.merets.library.repository")
@EnableWebFlux
public class LibraryReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryReactiveApplication.class, args);
    }

}
