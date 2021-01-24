package ru.itis.javalab.repositories;


import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    //language=SQL
    private static final String SQL_FIND_ALL = "select * from uzer";

    //language=SQL
    private static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "select * from uzer where login = ?";

    //language=SQL
    public static final String SQL_INSERT = "insert into uzer (login, password) values (?, ?)";

    private DataSource dataSource;
    private SimpleJdbcTemplate template;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getInt("id"))
            .login(row.getString("login"))
            .password(row.getString("password"))
            .build();

    @Override
    public void save(User entity) {
        template.queryWithoutResponse(SQL_INSERT, userRowMapper, entity.getLogin(), entity.getPassword());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }


    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, userRowMapper);

    }

    @Override
    public Optional<User> findByLogin(String login) {
        List<User> users = template.query(SQL_FIND_BY_LOGIN_AND_PASSWORD, userRowMapper, login);
        return Optional.ofNullable(
                users.size() < 1 ? null : users.get(0)
        );
    }

}
