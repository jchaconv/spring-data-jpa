package com.vilelo.sdjpa_jdbc.dao;

import com.vilelo.sdjpa_jdbc.domain.Author;

public interface AuthorDao {

    Author getAuthorById(Long id);

    Author getAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

}
