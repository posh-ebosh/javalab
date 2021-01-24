package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AuthRepositoriesImpl implements AuthRepositories<Cookie> {
    // language=SQL
    private static final String SQL_FIND_BY_UUID = "select * from auth where value = ?";

    private DataSource dataSource;

    public AuthRepositoriesImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    //извините, не смогла придумать как засунуть это в SimpleJdbcTemplate
    @Override
    public List<Cookie> findByCookieValue(String value) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_UUID);

            statement.setObject(1, value);
            resultSet = statement.executeQuery();

            List<Cookie> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(new Cookie("auth", resultSet.getString("value")));
            }

            return result;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
        }
    }

    @Override
    public void save(Cookie entity) {

    }

    @Override
    public void update(Cookie entity) {

    }

    @Override
    public void delete(Cookie entity) {

    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }
}
