package ru.otus.merets.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.merets.library.domain.Comment;
import ru.otus.merets.library.repository.CommentRepository;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final BookService bookService;
    private final IOService ioService;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void add(String book_id) {
        ioService.printMessage("Enter your comment: ");
        String comment = ioService.getString();
        commentRepository.save(new Comment("0", comment, bookService.getBookById(book_id)));
    }

    @Override
    @Transactional
    public void delete() {
        commentRepository.delete(getViaUI());
    }

    @Override
    @Transactional(readOnly = true)
    public void print() {
        commentRepository.findAll().forEach(ioService::printMessage);
        ioService.printMessage( getViaUI() );
    }

    @Override
    public Comment getViaUI() {
        ioService.printMessage("Enter comment's id: ");
        String value = ioService.getString();
        return commentRepository.findById(
                value)
                .orElseThrow(() -> new NoCommentException(String.format("There is not a comment with id %s", value)));
    }
}
