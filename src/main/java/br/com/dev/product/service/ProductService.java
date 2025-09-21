package br.com.dev.product.service;

import br.com.dev.product.entity.Product;
import br.com.dev.product.exception.BadRequestException;
import br.com.dev.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Mono<Product> insert(Mono<Product> productMono) {
        return productMono
            .flatMap(product ->
                validateName(product.getName())
                    .then(validatePrice(product.getPrice()))
                    .then(validateUniqueName(product.getName()))
                    .then(productRepository.save(product))
            );
    }

    private Mono<Void> validateName(String name) {
        if (name == null || name.isBlank()) {
            return Mono.error(new BadRequestException("Name not provided"));
        }
        if (name.length() < 3) {
            return Mono.error(new BadRequestException("Name has less than 3 characters"));
        }
        if (name.length() > 50) {
            return Mono.error(new BadRequestException("Name has more than 50 characters"));
        }
        return Mono.empty();
    }

    private Mono<Void> validatePrice(BigDecimal price) {
        if (price == null) {
            return Mono.error(new BadRequestException("Price not provided"));
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new BadRequestException("Price must be greater than zero"));
        }
        return Mono.empty();
    }

    private Mono<Void> validateUniqueName(String name) {
        return productRepository.findByName(name)
            .flatMap(existing -> Mono.error(new BadRequestException("A product with this name already exists")))
            .then();
    }
}
