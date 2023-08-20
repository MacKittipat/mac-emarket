package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

  Mono<CategoryDto> findById(String id);

  Flux<CategoryDto> findAll();

  Mono<CategoryDto> create(CategoryDto categoryDto);

  Mono<CategoryDto> update(CategoryDto categoryDto);

  Mono<Void> delete(String id);

}
