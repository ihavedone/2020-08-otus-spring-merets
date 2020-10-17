package ru.otus.merets.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.merets.library.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void delete(Comment comment) {
        em.remove(comment);
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
        return comment;
    }

    @Override
    public List<Comment> findAllByBookId(Long book_id) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book_id = :book_id", Comment.class);
        query.setParameter("book_id", book_id);
        return query.getResultList();
    }
}
