package ru.itis.javalab.services;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository usersRepository;

    public UserServiceImpl(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public  List<User> getAllUsersByAge(int age){
        return  usersRepository.findAllByAge(age);
    }
}

