package ru.otus.merets.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.merets.library.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if( book.getId()==0){
            em.persist(book);
        } else {
            em.merge(book);
        }
        return book;
    }

    @Override
    public Set<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return new HashSet<>( query.getResultList() );
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable( em.find(Book.class, id) );
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }
}
