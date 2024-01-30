package ru.otus.users.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.otus.users.exeption.ApplicationException;
import ru.otus.users.exeption.DataNotFoundException;
import ru.otus.users.web.response.DefaultResponse;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> handleConstraintViolationException(final DataNotFoundException e) {
        DefaultResponse response = new DefaultResponse(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<Object> handleApplicationException(final ApplicationException e) {
        DefaultResponse response = new DefaultResponse(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
