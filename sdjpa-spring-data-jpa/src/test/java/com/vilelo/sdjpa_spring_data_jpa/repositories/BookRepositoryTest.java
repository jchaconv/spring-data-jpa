package com.vilelo.sdjpa_spring_data_jpa.repositories;

import com.vilelo.sdjpa_spring_data_jpa.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_spring_data_jpa.repositories"})
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private final static String VALID_BOOK_TITLE = "Spring in Action, 6th Edition";

    @Test
    void findBookByTitleWithQueryNamed_Success() {
        Book book = bookRepository.findBookByTitleWithQueryNamed(VALID_BOOK_TITLE);
        assertThat(book).isNotNull();
    }

    @Test
    void findBookByTitleWithQuery_Success() {
        Book book = bookRepository.findBookByTitleWithQuery(VALID_BOOK_TITLE);
        assertThat(book).isNotNull();
    }

    @Test
    void queryByTitle_Future_Success() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture = bookRepository.queryByTitle(VALID_BOOK_TITLE);
        Book book = bookFuture.get();
        assertNotNull(book);
        assertEquals(VALID_BOOK_TITLE, book.getTitle());
    }

    @Test
    void findAllByTitleNotNull_Success() {

        AtomicInteger count = new AtomicInteger();

        bookRepository.findAllByTitleNotNull().forEach(book -> {
            count.incrementAndGet();
        });

        assertThat(count.get()).isGreaterThan(4);

    }

    @Test
    void findBookByTitle_NotFound() {
        Optional<Book> foobar3 = bookRepository.findBookByTitle("foobar3");
        assertFalse(foobar3.isPresent());
    }

    @Test
    void readByTitle_Null() {
        assertNull(bookRepository.readByTitle("foobar4"));
    }

    @Test
    void getByTitle_Success() {
        Book book = bookRepository.getByTitle(VALID_BOOK_TITLE);
        assertNotNull(book);
        assertNotNull(book.getTitle());
        assertEquals(VALID_BOOK_TITLE, book.getTitle());
    }

    @Test
    void getByTitle_Null() {
        assertNull(bookRepository.getByTitle(null));
    }

    @Test
    void getByTitle_NotFound() {
        assertNull(bookRepository.getByTitle("foo"));
    }

}