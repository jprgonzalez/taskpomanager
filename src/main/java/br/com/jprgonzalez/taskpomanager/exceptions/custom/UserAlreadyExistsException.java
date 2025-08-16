package br.com.jprgonzalez.taskpomanager.exceptions.custom;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
