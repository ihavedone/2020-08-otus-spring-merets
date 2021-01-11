package ru.otus.merets.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.merets.library.domain.User;

public interface UserRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
}
