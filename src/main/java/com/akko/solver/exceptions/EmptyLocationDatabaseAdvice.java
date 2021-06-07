package com.akko.solver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmptyLocationDatabaseAdvice {
    @ResponseBody
    @ExceptionHandler(EmptyLocationDatabase.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String emptyDataBaseHandler(EmptyLocationDatabase ex){
        return ex.getMessage();
    }
}
