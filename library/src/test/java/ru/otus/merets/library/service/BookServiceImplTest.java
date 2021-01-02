package ru.otus.merets.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.merets.library.domain.Book;
import ru.otus.merets.library.repository.BookRepository;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("BookServiceImpl should ")
class BookServiceImplTest {
    private static final String NEW_BOOK_CAPTION = "new book caption";
    private static final String NEW_BOOK_ID_OF_AUTHORS = "1";
    private static final String NEW_BOOK_ID_OF_GENRES = "1";
    private static final String DELETE_BOOK_ID = "2";

    @MockBean
    private IOService ioService;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    @DisplayName(" add a new book")
    void shouldAddNewBook() {
/*
        bookService.save( new Book(null, NEW_BOOK_CAPTION, NEW_BOOK_ID_OF_GENRES, NEW_BOOK_ID_OF_AUTHORS) );

        ArgumentCaptor<Book> requestCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository, atLeastOnce()).save(requestCaptor.capture());

        assertThat(requestCaptor.getAllValues().stream().map(Book::getCaption)).contains(NEW_BOOK_CAPTION);
*/
    }

    @Test
    @DisplayName(" delete the book")
    void shouldDeleteBook() {
        Book bookForDeleting = new Book(DELETE_BOOK_ID, "it does not matter", new HashSet<>(), new HashSet<>());

        given(ioService.getString()).willReturn(DELETE_BOOK_ID);
        given(bookRepository.findById(any())).willReturn(
                Optional.of(bookForDeleting));
        bookService.delete( bookForDeleting );

        ArgumentCaptor<String> requestCaptorId = ArgumentCaptor.forClass(String.class);
        verify(bookRepository, times(1)).findById(requestCaptorId.capture());

        ArgumentCaptor<Book> requestCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository, times(1)).delete(requestCaptor.capture());

        assertThat(requestCaptor.getValue().getId()).isEqualTo(DELETE_BOOK_ID);
    }
}