package com.vilelo.sdjpa_spring_data_jpa.dao;


import com.vilelo.sdjpa_spring_data_jpa.domain.Book;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_spring_data_jpa.dao"})
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
    void findBookByTitleNotFoundTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            bookDao.findBookByTitle("fake book");
        });
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

    /*
    @Test
    void findAllTest() {
        List<Book> books = bookDao.findAll();
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }
    */

}