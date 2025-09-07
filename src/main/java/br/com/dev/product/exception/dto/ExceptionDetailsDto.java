package br.com.dev.product.exception.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionDetailsDto(String error, Integer status, String path, LocalDateTime timestamp) {

}