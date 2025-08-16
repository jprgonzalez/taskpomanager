package br.com.jprgonzalez.taskpomanager.exceptions;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorModel {
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorModel(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
