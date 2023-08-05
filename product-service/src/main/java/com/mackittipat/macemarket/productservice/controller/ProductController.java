package com.mackittipat.macemarket.productservice.controller;

import com.mackittipat.macemarket.productservice.dto.ProductDto;
import com.mackittipat.macemarket.productservice.dto.ProductSearchDto;
import com.mackittipat.macemarket.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("")
  public Mono<ProductDto> createProduct(@RequestBody ProductDto productDto) {
    log.info("Creating a new product");
    return productService.create(productDto);
  }

  @GetMapping("{id}")
  public Mono<ProductDto> findById(@PathVariable String id) {
    log.info("Finding product with id = {}", id);
    return productService.findById(id);
  }

  @GetMapping("")
  public Flux<ProductDto> search(ProductSearchDto productSearchDto) {
    log.info("Searching products. {}", productSearchDto.toString());
    return productService.search(productSearchDto);
  }
}
