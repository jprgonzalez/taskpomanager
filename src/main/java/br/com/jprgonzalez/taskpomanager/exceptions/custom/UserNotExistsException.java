package br.com.jprgonzalez.taskpomanager.exceptions.custom;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String message) {
        super(message);
    }
}
