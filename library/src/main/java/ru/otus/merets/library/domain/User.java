package ru.otus.merets.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("user")
@Getter
@AllArgsConstructor
public class User {
    @Id
    private final ObjectId _id;

    private final String username;
    private final String password;
    private final Set<String> roles;
}
