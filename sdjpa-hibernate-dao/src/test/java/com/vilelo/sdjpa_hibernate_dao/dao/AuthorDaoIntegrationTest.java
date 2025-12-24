package com.vilelo.sdjpa_hibernate_dao.dao;

import com.vilelo.sdjpa_hibernate_dao.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.vilelo.sdjpa_hibernate_dao.dao"})
@DataJpaTest
class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

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
        Author deleted = authorDao.getAuthorById(saved.getId());
        assertThat(deleted).isNull();
    }


}