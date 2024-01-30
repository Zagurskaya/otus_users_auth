package ru.otus.users.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.users.web.response.DefaultResponse;

@RestController
@RequestMapping("/")
public class DataController {

    @GetMapping()
    public DefaultResponse health() {
        return new DefaultResponse("OK");
    }
}
