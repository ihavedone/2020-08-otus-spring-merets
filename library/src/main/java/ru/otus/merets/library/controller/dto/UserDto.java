package ru.otus.merets.library.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class UserDto {
    private final String username;
    private final Boolean authenticated;
    private final String token;
    private final List<String> roles;
}
