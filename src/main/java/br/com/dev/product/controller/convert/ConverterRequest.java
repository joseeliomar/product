package br.com.dev.product.controller.convert;

import br.com.dev.product.controller.data.request.ProductRequest;
import br.com.dev.product.entity.Product;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ConverterRequest {
    public Mono<Product> requestToEntity(ProductRequest productRequest) {
        return Mono.justOrEmpty(productRequest)
                .map(req -> Product.builder()
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .build());
    }
}
