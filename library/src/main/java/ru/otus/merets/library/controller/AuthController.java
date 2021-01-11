package ru.otus.merets.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.merets.library.controller.dto.UserDto;

import java.util.stream.Collectors;

@RestController
@Slf4j
public class AuthController {
    @GetMapping("/api/auth")
    public UserDto auth(){
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return UserDto.builder()
                    .authenticated(true)
                    .username(userDetails.getUsername() )
                    .roles(userDetails.getAuthorities().stream().map( Object::toString).collect(Collectors.toList()))
                    .token( ((WebAuthenticationDetails)authentication.getDetails()).getSessionId() )
                    .build();
        } catch (Exception e)
        {
            log.error("Error in auth controller",e);
            return UserDto.builder().authenticated(false).build();
        }
    }
}
