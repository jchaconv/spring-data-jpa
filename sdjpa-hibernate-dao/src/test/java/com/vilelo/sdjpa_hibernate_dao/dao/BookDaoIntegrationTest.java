package com.vilelo.sdjpa_hibernate_dao.dao;

import com.vilelo.sdjpa_hibernate_dao.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_hibernate_dao.dao"})
@DataJpaTest
class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Test
    void getBookByIdTest() {
        Book book = bookDao.getById(1L);
        assertThat(book).isNotNull();
    }

    @Test
    void findBookByTitleTest() {
        Book book = bookDao.findBookByTitle("Spring in Action, 6th Edition");
        assertThat(book).isNotNull();
    }

    @Test
    void saveNewBookTest() {
        Book book = new Book();
        book.setTitle("Book test");
        book.setIsbn("132456770312");
        book.setPublisher("Publisher test");
        book.setAuthorId(1L);
        assertThat(bookDao.saveNewBook(book)).isNotNull();
    }

    @Test
    void testUpdateBook() {
        Book newBook = new Book("Book test 2", "132456770313", "Publisher test 2");
        newBook.setAuthorId(2L);
        Book saved = bookDao.saveNewBook(newBook);
        saved.setTitle("Book test 3");

        Book updated = bookDao.updateBook(saved);
        assertThat(updated.getTitle()).isEqualTo(saved.getTitle());
    }

    @Test
    void testDeleteBookById() {
        Book saved = bookDao.saveNewBook(new Book("Book test 4", "132456770314", "Publisher test 4"));
        bookDao.deleteBookById(saved.getId());
        Book deleted = bookDao.getById(saved.getId());
        assertThat(deleted).isNull();
    }

}