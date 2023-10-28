package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.dto.ProductDto;
import com.mackittipat.macemarket.productservice.dto.ProductSearchDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductDto> findById(String id);

    Flux<ProductDto> findAll();

    Flux<ProductDto> search(ProductSearchDto productSearchDto);

    Mono<ProductDto> create(ProductDto productDto);

    Mono<ProductDto> update(ProductDto productDto);

    Mono<Void> delete(String id);
}
