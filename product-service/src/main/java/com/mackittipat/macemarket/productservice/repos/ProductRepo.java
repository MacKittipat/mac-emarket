package com.mackittipat.macemarket.productservice.repos;

import com.mackittipat.macemarket.productservice.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepo extends ReactiveMongoRepository<Product, String> {

}
