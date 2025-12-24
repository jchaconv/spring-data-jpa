package com.vilelo.sdjpa_jdbc.dao;

import com.vilelo.sdjpa_jdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_jdbc.dao"})
@DataJpaTest
class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void getAuthorByIdTest() {
        Author author = authorDao.getAuthorById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    void getAuthorByNameTest() {
        Author author = authorDao.getAuthorByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void saveNewAuthorTest() {
        Author author = authorDao.saveNewAuthor(new Author("Julio", "Chacon"));
        System.out.println("=== New id: " + author.getId());
        assertThat(author).isNotNull();
    }

    @Test
    void testUpdateAuthor() {
        Author saved = authorDao.saveNewAuthor(new Author("Pharrell", "Collins"));
        saved.setLastName("Williams");

        Author updated = authorDao.updateAuthor(saved);
        assertThat(updated.getLastName()).isEqualTo(saved.getLastName());
    }

    @Test
    void testDeleteAuthorById() {
        Author saved = authorDao.saveNewAuthor(new Author("Rut", "Ludena"));
        authorDao.deleteAuthorById(saved.getId());
        assertThrows(EmptyResultDataAccessException.class, () -> {
            authorDao.getAuthorById(saved.getId());
        });
    }


}