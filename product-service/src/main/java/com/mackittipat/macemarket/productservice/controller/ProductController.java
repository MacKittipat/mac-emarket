package com.mackittipat.macemarket.productservice.controller;

import com.mackittipat.macemarket.productservice.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "products")
public class ProductController {

  @PostMapping("")
  public Mono<Product> createProduct() {
    log.info("Creating a new product");
    return Mono.just(
        new Product());
  }

  @GetMapping("{id}")
  public Mono<Product> getProductById(@PathVariable String id) {
    log.info("Finding product with id = {}", id);
    return Mono.just(
        new Product());
  }
}
