package ru.otus.users.service;

import ru.otus.users.exeption.UserNotFoundException;
import ru.otus.users.model.User;

public interface UserService {

    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;
}
