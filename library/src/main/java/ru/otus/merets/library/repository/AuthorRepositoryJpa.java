package ru.otus.merets.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.merets.library.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Author> findByIds(List<Long> ids) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id in (:ids)", Author.class);
        query.setParameter("ids", ids);
        return new HashSet<>(query.getResultList());
    }
}
