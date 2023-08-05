package com.mackittipat.macemarket.productservice.repos;

import com.mackittipat.macemarket.productservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class CustomProductRepoImpl implements CustomProductRepo {

    @Autowired
    private ReactiveMongoOperations mongoTemplate;

    @Override
    public Flux<Product> search(Query query) {
        return mongoTemplate.find(query, Product.class);
    }
}
