package com.solid12.solid12.controller.exception_handler;

import com.solid12.solid12.controller.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CitizenNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFound(CitizenNotFoundException ex) {
        Response error = new Response("404",  "citizen not found", null, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CitizenAlreadyExist.class)
    public ResponseEntity<Response> handleCitizenAlreadyExist(CitizenAlreadyExist ex) {
        Response error = new Response("409","citizen already exists",null, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneralException(Exception ex) {
        Response error = new Response("500", "Internal Server Error", null, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
