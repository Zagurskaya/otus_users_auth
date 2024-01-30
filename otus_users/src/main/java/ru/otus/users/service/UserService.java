package ru.otus.users.service;

import ru.otus.users.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    void add(UserDto user);

    void update(UserDto user);

    UserDto findUserById(Integer id);

    void deleteUserById(Integer id);
}
