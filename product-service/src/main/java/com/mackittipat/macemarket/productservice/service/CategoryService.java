package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.entity.Category;
import reactor.core.publisher.Mono;

public interface CategoryService {

  Mono<Category> create(Category category);

  Mono<Category> edit(Category category);

}
