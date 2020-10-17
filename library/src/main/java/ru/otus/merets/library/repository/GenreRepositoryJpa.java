package ru.otus.merets.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.merets.library.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Genre> findByIds(List<Long> ids) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.id in (:ids)", Genre.class);
        query.setParameter("ids", ids);
        return new HashSet<>(query.getResultList());
    }
}
