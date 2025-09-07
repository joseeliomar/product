package br.com.dev.product.controller;

import br.com.dev.product.controller.convert.ConverterRequest;
import br.com.dev.product.controller.convert.ConverterResponse;
import br.com.dev.product.controller.data.request.ProductRequest;
import br.com.dev.product.controller.data.response.ProductResponse;
import br.com.dev.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ConverterRequest converterRequest;
    private final ConverterResponse converterResponse;

    @PostMapping("/insert")
    public Mono<ProductResponse> inserirProduto(@RequestBody ProductRequest productRequest) {
        return Mono.justOrEmpty(productRequest)
                .flatMap(req ->
                        converterResponse.responseToEntity(
                                productService.insert(
                                        converterRequest.requestToEntity(req))));
    }

}
