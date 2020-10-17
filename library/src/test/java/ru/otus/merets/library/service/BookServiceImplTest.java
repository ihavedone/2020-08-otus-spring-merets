package ru.otus.merets.library.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.repository.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceImplTest {
    private static final String NEW_BOOK_CAPTION = "new book caption";
    private static final Long ID_BOOK_TO_DELETE = 1L;

    @MockBean
    private IOService ioService;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void addNewBook() {
        given( ioService.getString() ).willReturn(NEW_BOOK_CAPTION, "1", "1");
        bookService.addNewBook();

        ArgumentCaptor<Book> requestCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository, times(1)).save( requestCaptor.capture() );

        assertThat( requestCaptor.getValue().getCaption() ).isEqualTo(NEW_BOOK_CAPTION);
    }
}