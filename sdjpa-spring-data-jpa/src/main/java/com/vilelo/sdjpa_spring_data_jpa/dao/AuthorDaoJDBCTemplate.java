package com.vilelo.sdjpa_spring_data_jpa.dao;

import com.vilelo.sdjpa_spring_data_jpa.domain.Author;
import com.vilelo.sdjpa_spring_data_jpa.mapper.AuthorMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Objects;

public class AuthorDaoJDBCTemplate implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM author WHERE last_name = ?");

        if(pageable.getSort().getOrderFor("myName") != null) {
            sb.append(" ORDER BY first_name ")
                    .append(Objects.requireNonNull(pageable.getSort().getOrderFor("myName")).getDirection().name());
        }

        sb.append(" LIMIT ? OFFSET ?");

        return jdbcTemplate.query(sb.toString(), getAuthorMapper(), lastName, pageable.getPageSize(), pageable.getOffset());
    }

    private AuthorMapper getAuthorMapper() {
        return new AuthorMapper();
    }

    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }
}
