package br.com.dev.product.service;

import br.com.dev.product.entity.Product;
import br.com.dev.product.exception.InternalServerErrorException;
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
            return Mono.error(new InternalServerErrorException("Nome não informado"));
        }
        if (name.length() < 3) {
            return Mono.error(new InternalServerErrorException("Nome com menos de 3 caracteres"));
        }
        if (name.length() > 50) {
            return Mono.error(new InternalServerErrorException("Nome com mais de 50 caracteres"));
        }
        return Mono.empty();
    }

    private Mono<Void> validatePrice(BigDecimal price) {
        if (price == null) {
            return Mono.error(new InternalServerErrorException("Preço não informado"));
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new InternalServerErrorException("Preço deve ser maior que zero"));
        }
        return Mono.empty();
    }

    private Mono<Void> validateUniqueName(String name) {
        return productRepository.findByName(name)
            .flatMap(existing -> Mono.error(new InternalServerErrorException("Já existe um produto com esse nome")))
            .then();
    }
}
