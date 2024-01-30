package ru.otus.users.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.users.model.dto.UserDto;
import ru.otus.users.service.UserService;
import ru.otus.users.web.response.DefaultResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        UserDto userDto = userService.findUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> saveUser(@RequestBody @Valid UserDto userDto) {
        userService.add(userDto);
        return new ResponseEntity<>(new DefaultResponse("successful operation"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtoList = userService.getUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(new DefaultResponse("user deleted"),HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateUser(@PathVariable("id") Integer id,
                                             @RequestBody @Valid UserDto userDto) {
        if (id == null) {
            return new ResponseEntity<>(new DefaultResponse(HttpStatus.BAD_REQUEST.value(),"Incorrect user id"),
                    HttpStatus.BAD_REQUEST);
        }
        userDto.setId(id);
        userService.update(userDto);
        return new ResponseEntity<>(new DefaultResponse("user updated"),HttpStatus.OK);
    }
}
