package ru.otus.merets.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.merets.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedJdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public Set<Author> getAll() {
        return new HashSet<>(namedJdbc.query("SELECT a.id, a.name FROM author a", new AuthorMapper()));
    }

    @Override
    public Set<Author> getByIds(List<Long> ids) {
        return new HashSet<>(namedJdbc.query("SELECT a.id, a.name FROM author a WHERE a.id IN (:ids)",
                Map.of("ids", ids),
                new AuthorMapper()));
    }

    private static final class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getLong("id"), resultSet.getString("name"));
        }
    }
}
