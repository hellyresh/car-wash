package com.example.carwash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String resolveException(Exception e) {
        return e.toString();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream().map(cv -> cv.getPropertyPath().toString() +
                ": " + cv.getMessage()).toList().toString();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(HttpMessageNotReadableException e) {
        return "Parsing exception, make sure the data is correct";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage()).toList().toString();
    }


    @ExceptionHandler(CustomAccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String resolveException(CustomAccessDeniedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {
            AvailableBoxNotFoundException.class,
            AuthException.class,
            BoxAlreadyHasOperatorException.class,
            CheckInNotAvailableException.class,
            OrderCannotBeChangedException.class,
            RoleCannotBeChangedException.class,
            UsernameAlreadyExistsException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resolveException(Throwable e) {
        return e.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resolveException(EntityNotFoundException e) {
        return e.getMessage();
    }


}
