package br.com.dev.product.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ExceptionWithHttpStatus {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, HttpStatus.BAD_REQUEST);
    }
}
