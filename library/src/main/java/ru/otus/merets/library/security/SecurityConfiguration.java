package ru.otus.merets.library.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MongoUserDetailsService userDetailsService;

    /**
     * En: It is not necessary in this case, but this could be useful in the future
     * Ru: в данном проекте от этого исключения нет смысла, но специально не удаляю,
     * может понадобится в будущем
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/");
    }

    /**
     * En: restrict the access to API-endpoint and put some custom handlers for SPA.
     * Add new header to transfer cookie (session id)
     * Ru: прикрываем доступ к API-хэндрелам и навешиваем несколько кастомных обработчиков,
     * чтобы корректно отвечать для SPA приложения (без редиректов).
     * Добавляем новый заголовок для передачи id сессии
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/api/**"))
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests().antMatchers("/api/**").authenticated()
                .and()
                .formLogin()
                .failureHandler( ((request, response, exception) -> response.setStatus(HttpStatus.UNAUTHORIZED.value())))
                .successHandler( ((request, response, authentication) -> {
                    response.setStatus(200);
                    String jsessionidCookie = response.getHeader("set-cookie");
                    String[] partOfCookie = jsessionidCookie.split(";");
                    String jsessionid = partOfCookie[0].replaceAll("JSESSIONID=","");
                    response.setHeader("authorization", jsessionid );
                }) )
                .and()
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessHandler(
                                (request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)
                        )
                );
    }

    @Bean
    public AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 65536, 3);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
