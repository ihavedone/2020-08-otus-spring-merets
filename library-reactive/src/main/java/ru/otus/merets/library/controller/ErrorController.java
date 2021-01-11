package ru.otus.merets.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;
import ru.otus.merets.library.controller.dto.ErrorDto;

/**
 * En: It's a bad practice to show internal errors in front-end
 * Ru: Обернем ответ об ошибках контроллеров статичным шаблоном сообщения об ошибке.
 * Для минимизации отображения внутренней информации во фронте, внешнему пользователю это
 * все знать ни к чему
 */
@ControllerAdvice
@Slf4j
public class ErrorController {
    @ExceptionHandler
    public Mono<ResponseEntity<ErrorDto>> error(Exception ex) {
        log.error("Error in a controller: ", ex);
        return Mono.just( ResponseEntity
                .status(400)
                .body( new ErrorDto("Error in a controller. Please, check the log") ) );
    }
}
