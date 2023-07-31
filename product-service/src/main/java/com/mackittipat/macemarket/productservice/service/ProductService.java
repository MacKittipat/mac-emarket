package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductDto> findById(String id);

    Flux<ProductDto> findAll();

    Mono<ProductDto> create(ProductDto productDto);

    Mono<ProductDto> update(ProductDto productDto);
}
