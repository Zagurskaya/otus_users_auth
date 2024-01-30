package ru.otus.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.users.converter.UserConverter;
import ru.otus.users.exeption.DataNotFoundException;
import ru.otus.users.model.dto.UserDto;
import ru.otus.users.model.entity.User;
import ru.otus.users.repository.UserRepository;
import ru.otus.users.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.users.constant.ErrorCodeConstant.USER_NOT_FOUND_ERROR_CODE;

@Service
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(UserDto userDTO) {
        User user = userConverter.toEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(UserDto userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND_ERROR_CODE,"User not found by id = " + userDTO.getId()));
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserById(Integer id) {
        User loaded = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND_ERROR_CODE,"User not found by id = " + id));
        return userConverter.toDTO(loaded);
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND_ERROR_CODE,"User not found by id = " + id));
        userRepository.delete(user);
    }

}
