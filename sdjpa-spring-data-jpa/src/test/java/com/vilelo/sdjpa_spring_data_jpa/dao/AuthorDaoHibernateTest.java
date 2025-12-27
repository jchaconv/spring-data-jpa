package com.vilelo.sdjpa_spring_data_jpa.dao;

import com.vilelo.sdjpa_spring_data_jpa.domain.Author;
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
class AuthorDaoHibernateTest {

    @Autowired
    EntityManagerFactory emf;

    AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao = new AuthorDaoHibernate(emf);
    }

    @Test
    void findAllAuthorsByLastName_DescOrder() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("Walls", PageRequest.of(0, 10,
                Sort.by(Sort.Order.desc("myfirstname"))));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(5);
        assertEquals("Sonny", authors.get(0).getFirstName());
    }

    @Test
    void findAllAuthorsByLastName_AscOrder() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("Walls", PageRequest.of(0, 10,
                Sort.by(Sort.Order.asc("myfirstname"))));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(5);
        assertEquals("Craig", authors.get(0).getFirstName());
    }

}