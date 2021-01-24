package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

public interface AuthService {
    User login(String login);

    void signUp(String login, String password);
}
