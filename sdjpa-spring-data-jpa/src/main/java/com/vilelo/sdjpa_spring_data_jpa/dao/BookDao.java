package com.vilelo.sdjpa_spring_data_jpa.dao;

import com.vilelo.sdjpa_spring_data_jpa.domain.Book;

public interface BookDao {

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
