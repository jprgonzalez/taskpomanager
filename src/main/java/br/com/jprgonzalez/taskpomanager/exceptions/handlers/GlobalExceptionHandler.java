package br.com.jprgonzalez.taskpomanager.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jprgonzalez.taskpomanager.exceptions.ErrorModel;
import br.com.jprgonzalez.taskpomanager.exceptions.custom.UserAlreadyExistsException;
import br.com.jprgonzalez.taskpomanager.exceptions.custom.UserNotExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorModel> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ErrorModel apiError = new ErrorModel(
        HttpStatus.CONFLICT.value(),
        HttpStatus.CONFLICT.getReasonPhrase(),
        ex.getMessage()
    );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<ErrorModel> handleUserNotExists(UserNotExistsException ex) {
        ErrorModel apiError = new ErrorModel(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
