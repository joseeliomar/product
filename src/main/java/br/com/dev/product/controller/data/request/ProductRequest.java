package br.com.dev.product.controller.data.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
