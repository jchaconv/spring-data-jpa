package com.vilelo.sdjpa_spring_data_jpa.dao;

import com.vilelo.sdjpa_spring_data_jpa.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_spring_data_jpa.dao"})
@DataJpaTest
class BookDaoJDBCTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDaoJDBCTemplate(jdbcTemplate);
    }

    @Test
    void findAllBooks_Page1_Pageable_Success() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(1, 10));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void findAllBooks_Page1_Success() {
        List<Book> books = bookDao.findAllBooks(10, 0);
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void findAllBooks_Page2_Success() {
        List<Book> books = bookDao.findAllBooks(10, 10);
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void findAllBooks_Success() {
        List<Book> books = bookDao.findAllBooks();
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(4);
    }

    @Test
    void getById() {
        Book book = bookDao.getById(3L);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    void findBookByTitle() {
        Book book = bookDao.findBookByTitle("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void saveNewBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);

        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void updateBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());
        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    void deleteBookById() {

        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            bookDao.getById(saved.getId());
        });
    }


}