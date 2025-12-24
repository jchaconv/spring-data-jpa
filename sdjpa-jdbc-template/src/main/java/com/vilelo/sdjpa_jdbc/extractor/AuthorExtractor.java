package com.vilelo.sdjpa_jdbc.extractor;

import com.vilelo.sdjpa_jdbc.domain.Author;
import com.vilelo.sdjpa_jdbc.domain.Book;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorExtractor implements ResultSetExtractor<Author> {

    @Override
    public Author extractData(ResultSet rs) throws SQLException {
        /*rs.next();
        return new AuthorMapper().mapRow(rs, 0);*/
        Author author = null;

        while (rs.next()) {
            if (author == null) {
                author = new Author();
                author.setId(rs.getLong("id"));
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setBooks(new ArrayList<>());
            }

            if (rs.getString("isbn") != null) {
                Book book = new Book();
                book.setId(rs.getLong("book_id"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublisher(rs.getString("publisher"));
                book.setTitle(rs.getString("title"));
                book.setAuthorId(author.getId());

                author.getBooks().add(book);
            }
        }

        return author;
    }
}
