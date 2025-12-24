package com.vilelo.sdjpa_jdbc.dao;

import com.vilelo.sdjpa_jdbc.domain.Author;
import com.vilelo.sdjpa_jdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_jdbc.dao"})
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
        Book book = bookDao.saveNewBook(new Book("Book test", "132456770312", "Publisher test", getAuthor()));
        assertThat(book).isNotNull();
    }

    @Test
    void testUpdateBook() {

        Book saved = bookDao.saveNewBook(new Book("Book test 2", "132456770313", "Publisher test 2", getAuthor()));
        saved.setTitle("Book test 3");

        Book updated = bookDao.updateBook(saved);
        assertThat(updated.getTitle()).isEqualTo(saved.getTitle());
    }

    @Test
    void testDeleteBookById() {
        Book saved = bookDao.saveNewBook(new Book("Book test 4", "132456770314", "Publisher test 4", getAuthor()));
        bookDao.deleteBookById(saved.getId());
        Book deleted = bookDao.getById(saved.getId());
        assertThat(deleted).isNull();
    }

    private Author getAuthor() {
        Author author = new Author();
        author.setId(3L);
        return author;
    }


}