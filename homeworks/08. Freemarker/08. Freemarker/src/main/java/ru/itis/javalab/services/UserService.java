package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByLogin(String login);
}

