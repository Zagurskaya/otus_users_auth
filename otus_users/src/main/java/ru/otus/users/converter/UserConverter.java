package ru.otus.users.converter;

import ru.otus.users.model.dto.UserDto;
import ru.otus.users.model.entity.User;

public interface UserConverter {

    UserDto toDTO(User user);

    User toEntity(UserDto userDTO);

}
