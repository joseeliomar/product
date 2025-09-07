package br.com.dev.product.exception.handler;

import br.com.dev.product.exception.ExceptionWithHttpStatus;
import br.com.dev.product.exception.dto.ExceptionDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(value = { ExceptionWithHttpStatus.class })
    public Mono<ResponseEntity<ExceptionDetailsDto>> handleExceptionWithHttpStatus(
            ExceptionWithHttpStatus thrownException,
            ServerWebExchange exchange) {
        return buildBodyResponse(exchange, thrownException)
                .flatMap(body -> buildResponse(thrownException, body));
    }

    private Mono<ResponseEntity<ExceptionDetailsDto>> buildResponse(ExceptionWithHttpStatus thrownException, ExceptionDetailsDto body) {
        return Mono.just(ResponseEntity
                .status(thrownException.getHttpStatus())
                .body(body));
    }

    private Mono<ExceptionDetailsDto> buildBodyResponse(ServerWebExchange exchange, ExceptionWithHttpStatus t) {
        return Mono.just(ExceptionDetailsDto.builder()
                .error(t.getMessage())
                .status(t.getHttpStatus().value())
                .path(exchange.getRequest().getPath().value())
                .timestamp(LocalDateTime.now())
                .build());
    }
}
