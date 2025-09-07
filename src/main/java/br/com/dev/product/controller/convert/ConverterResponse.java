package br.com.dev.product.controller.convert;

import br.com.dev.product.controller.data.response.ProductResponse;
import br.com.dev.product.entity.Product;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ConverterResponse {
    public Mono<ProductResponse> responseToEntity(Mono<Product> productMono) {
        return productMono
                .map(ent -> ProductResponse.builder()
                        .id(ent.getId())
                        .name(ent.getName())
                        .description(ent.getDescription())
                        .price(ent.getPrice())
                        .createdAt(ent.getCreatedAt())
                        .updatedAt(ent.getUpdatedAt())
                        .build());
    }
}
