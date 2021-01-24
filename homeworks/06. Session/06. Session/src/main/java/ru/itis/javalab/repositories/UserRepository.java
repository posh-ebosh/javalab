package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends CrudRepository<User>{

    Optional<User> findByLogin(String login);

}
