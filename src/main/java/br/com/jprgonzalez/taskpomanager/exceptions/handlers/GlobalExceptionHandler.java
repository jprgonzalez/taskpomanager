package br.com.jprgonzalez.taskpomanager.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jprgonzalez.taskpomanager.exceptions.ErrorModel;
import br.com.jprgonzalez.taskpomanager.exceptions.custom.UserAlreadyExistsException;

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
    
}
