package ru.otus.merets.library.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            response.setStatus(200);
            String jsessionidCookie = response.getHeader("set-cookie");
            String[] partOfCookie = jsessionidCookie.split(";");
            String jsessionid = partOfCookie[0].replaceAll("JSESSIONID=","");
            response.setHeader("authorization", jsessionid );
    }
}
