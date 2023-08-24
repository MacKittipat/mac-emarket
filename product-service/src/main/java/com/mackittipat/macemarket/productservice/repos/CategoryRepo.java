package com.mackittipat.macemarket.productservice.repos;

import com.mackittipat.macemarket.productservice.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CategoryRepo extends ReactiveMongoRepository<Category, String> {

    @Query("{$or: [{'level': 0}, {'level': 1}]}")
    Flux<Category> findAllParent(Sort sort);
}
