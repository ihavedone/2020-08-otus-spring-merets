package ru.otus.merets.library.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import ru.otus.merets.library.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MongoUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.otus.merets.library.domain.User user = userRepository.findByUsername(username);

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
