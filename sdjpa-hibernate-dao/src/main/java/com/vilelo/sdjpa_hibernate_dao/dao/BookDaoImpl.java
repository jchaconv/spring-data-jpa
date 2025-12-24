package com.vilelo.sdjpa_hibernate_dao.dao;

import com.vilelo.sdjpa_hibernate_dao.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public Book saveNewBook(Book book) {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }
}
