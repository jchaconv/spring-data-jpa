package com.vilelo.sdjpa_spring_data_jpa.dao;

import com.vilelo.sdjpa_spring_data_jpa.domain.Book;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_spring_data_jpa.dao"})
@DataJpaTest
class BookDaoHibernateTest {

    @Autowired
    EntityManagerFactory emf;

    BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDaoHibernate(emf);
    }

    @Test
    void findAllBooks_SortByTitle() {
        List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0, 10,
                Sort.by(Sort.Order.desc("title"))));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
        assertEquals("You Don’t Know JS Yet", books.get(0).getTitle());
    }

    @Test
    void findAllBooks() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(1, 10));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }


}