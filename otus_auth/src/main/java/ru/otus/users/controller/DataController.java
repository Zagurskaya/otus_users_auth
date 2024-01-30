package ru.otus.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.users.response.DefaultResponse;

@RestController
@RequestMapping("/test")
public class DataController {

    @GetMapping()
    public DefaultResponse health() {
        return new DefaultResponse("OK");
    }
}
