package com.vilelo.sdjpa_spring_data_jpa.dao;

import com.vilelo.sdjpa_spring_data_jpa.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_spring_data_jpa.dao"})
@DataJpaTest
class AuthorDaoJDBCTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    AuthorDao authorDao;

    private final static String TEST_LAST_NAME = "Martin";

    @BeforeEach
    void setUp() {
        authorDao = new AuthorDaoJDBCTemplate(jdbcTemplate);
    }

    @Test
    void findAllAuthorsByLastName() {
        List<Author> authors = authorDao.findAllAuthorsByLastName(TEST_LAST_NAME, PageRequest.of(0, 10));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(9);
    }

    @Test
    void findAllAuthorsByLastName_SortFirstNameDesc() {
        List<Author> authors = authorDao.findAllAuthorsByLastName(TEST_LAST_NAME, PageRequest.of(0, 10,
                Sort.by(Sort.Direction.DESC, "myName")));
        assertThat(authors).isNotNull();
        assertThat(authors.get(0).getFirstName()).isEqualTo("Samuel");
    }

    @Test
    void findAllAuthorsByLastName_SortFirstNameAsc() {
        List<Author> authors = authorDao.findAllAuthorsByLastName(TEST_LAST_NAME, PageRequest.of(0, 10,
                Sort.by(Sort.Order.asc("myName"))));
        assertThat(authors).isNotNull();
        assertThat(authors.get(0).getFirstName()).isEqualTo("Beatriz");
    }

    @Test
    void findAllAuthorsByLastName_AllRecs() {
        List<Author> authors = authorDao.findAllAuthorsByLastName(TEST_LAST_NAME, PageRequest.of(0, 100));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(9);
    }

}