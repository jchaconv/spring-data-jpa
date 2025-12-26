package com.vilelo.sdjpa_spring_data_jpa.dao;


import com.vilelo.sdjpa_spring_data_jpa.domain.Author;
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
class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;


    @Test
    void getAuthorByIdTest() {
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    void getAuthorByNameTest() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void getAuthorByNameNotFoundTest() {
        assertThrows(EntityNotFoundException.class, () -> {
            authorDao.findAuthorByName("Maelo", "Ruiz");
        });
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
        Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();
    }

    /*

    @Test
    void findAllTest() {
        List<Author> authors = authorDao.findAll();
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void listAuthorsByLastNameLikeTest() {
        List<Author> authors = authorDao.listAuthorByLastNameLike("Wall");
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void getAuthorByNameCriteriaTest() {
        Author author = authorDao.findAuthorByNameCriteria("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void getAuthorByNameNativeTest() {
        Author author = authorDao.findAuthorByNameNative("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    */

}