package com.vilelo.sdjpa_hibernate_dao.dao;

import com.vilelo.sdjpa_hibernate_dao.domain.Author;

public interface AuthorDao {

    Author getAuthorById(Long id);

    Author getAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author saved);

    void deleteAuthorById(Long id);

}
