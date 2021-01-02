package ru.otus.merets.library.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private Boolean status;
    private String message;
}
