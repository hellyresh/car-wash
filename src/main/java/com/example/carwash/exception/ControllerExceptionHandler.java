package com.example.carwash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String resolveException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage()).toList().toString();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(AccessDeniedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AvailableBoxNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(AvailableBoxNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(AuthException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BoxAlreadyHasOperatorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(BoxAlreadyHasOperatorException e) {
        return e.getMessage();
    }

    @ExceptionHandler(CheckInNotAvailableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resolveException(CheckInNotAvailableException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resolveException(EntityNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(OrderCannotBeChangedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(OrderCannotBeChangedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(RoleCannotBeChangedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(RoleCannotBeChangedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(UsernameAlreadyExistsException e) {
        return e.getMessage();
    }

}
