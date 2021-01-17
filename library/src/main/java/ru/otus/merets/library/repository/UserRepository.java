package ru.otus.merets.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.merets.library.domain.CustomUser;

import java.util.Optional;

public interface UserRepository extends MongoRepository<CustomUser,String> {
    Optional<CustomUser> findByUsername(String username);
}
