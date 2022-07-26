package org.lisiecki.hsbcchallenge.handlers;

import org.lisiecki.hsbcchallenge.exceptions.ApplicationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String GLOBAL_MESSAGE = "System error. Please contact Application administration";

    @ExceptionHandler(value = {RuntimeException.class, NullPointerException.class})
    protected ResponseEntity<Object> handleGlobalExceptions(Exception exception, WebRequest request) {
        return handleExceptionInternal(exception, GLOBAL_MESSAGE, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = ApplicationException.class)
    protected ResponseEntity<Object> handleApplicationException(ApplicationException applicationException, WebRequest request) {
        return handleExceptionInternal(applicationException, applicationException.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}