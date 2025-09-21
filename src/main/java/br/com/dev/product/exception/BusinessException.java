package br.com.dev.product.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends ExceptionWithHttpStatus {
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
