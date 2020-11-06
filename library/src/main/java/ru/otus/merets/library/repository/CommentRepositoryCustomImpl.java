package ru.otus.merets.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.merets.library.domain.Comment;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void deleteByBook_Id(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("book.$id").is(id));
        mongoTemplate.remove(query, Comment.class);
    }
}
