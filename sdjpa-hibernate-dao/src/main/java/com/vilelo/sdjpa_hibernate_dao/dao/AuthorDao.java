package com.vilelo.sdjpa_hibernate_dao.dao;

import com.vilelo.sdjpa_hibernate_dao.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author findAuthorByNameCriteria(String firstName, String lastName);

    Author findAuthorByNameNative(String firstName, String lastName);

    List<Author> findAll();

    List<Author> listAuthorByLastNameLike(String lastName);

    Author getAuthorById(Long id);

    Author getAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author saved);

    void deleteAuthorById(Long id);

}
