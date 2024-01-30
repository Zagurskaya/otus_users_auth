package ru.otus.users.config;

import ru.otus.users.model.User;

import java.util.Map;

public interface JwtGeneratorInterface {

    Map<String, String> generateToken(User user);
}
