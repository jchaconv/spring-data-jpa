package com.vilelo.sdjpa_jdbc.dao;

import com.vilelo.sdjpa_jdbc.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource source;

    public AuthorDaoImpl(DataSource source) {
        this.source = source;
    }

    @Override
    public Author getAuthorByName(String firstName, String lastName) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM author where first_name = ? and last_name = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    @Override
    public Author getAuthorById(Long id) {

        Connection connection = null;
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("SELECT * FROM author where id = ?");
            preparedStatement.setLong(1, id);
            // resultSet = statement.executeQuery("SELECT * FROM author where id = " + id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO author (first_name, last_name) values (?, ?)");
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.execute();

            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");

            if (resultSet.next()) {
                Long savedId = resultSet.getLong(1);
                statement.close();
                return this.getAuthorById(savedId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = source.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE author SET first_name = ?, last_name = ? WHERE id = ?");
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setLong(3, author.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.getAuthorById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = source.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM author WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(null, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        return author;
    }

    private void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) throws SQLException {
        if(resultSet != null) {
            resultSet.close();
        }
        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }
    }

}
