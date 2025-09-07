package br.com.dev.product.exception;

import org.springframework.http.HttpStatus;

public abstract class ExceptionWithHttpStatus extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected HttpStatus httpStatus;

    public ExceptionWithHttpStatus(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ExceptionWithHttpStatus(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}