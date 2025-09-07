package br.com.dev.product.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends ExceptionWithHttpStatus {
    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
