package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import reactor.core.publisher.Mono;

public interface CategoryService {

  Mono<CategoryDto> findById(String id);

  Mono<CategoryDto> create(CategoryDto categoryDto);

  Mono<CategoryDto> edit(CategoryDto categoryDto);

}
