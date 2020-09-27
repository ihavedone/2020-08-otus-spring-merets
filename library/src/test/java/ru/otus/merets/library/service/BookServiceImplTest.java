package ru.otus.merets.library.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.merets.library.dao.AuthorDao;
import ru.otus.merets.library.dao.BookDao;
import ru.otus.merets.library.dao.GenreDao;
import ru.otus.merets.library.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceImplTest {
    private static final String NEW_BOOK_CAPTION = "new book caption";

    @MockBean
    private IOService ioService;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void addNewBook() {
        given( ioService.getString() ).willReturn(NEW_BOOK_CAPTION, "1", "1");
        bookService.addNewBook();

        ArgumentCaptor<Book> requestCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookDao, times(1)).insert( requestCaptor.capture() );

        assertThat( requestCaptor.getValue().getCaption() ).isEqualTo(NEW_BOOK_CAPTION);
    }
}