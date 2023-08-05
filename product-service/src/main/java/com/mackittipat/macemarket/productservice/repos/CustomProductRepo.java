package com.mackittipat.macemarket.productservice.repos;

import com.mackittipat.macemarket.productservice.entity.Product;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public interface CustomProductRepo {

    Flux<Product> search(Query query);
}
