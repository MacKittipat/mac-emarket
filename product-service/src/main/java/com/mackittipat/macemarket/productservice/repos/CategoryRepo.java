package com.mackittipat.macemarket.productservice.repos;

import com.mackittipat.macemarket.productservice.entity.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepo extends ReactiveMongoRepository<Category, String> {
}
