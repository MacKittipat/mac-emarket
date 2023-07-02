package com.mackittipat.macemarket.productservice.controller;

import com.mackittipat.macemarket.productservice.entity.Category;
import com.mackittipat.macemarket.productservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "categories")
public class CategoryController {

  @Autowired private CategoryService categoryService;

  @PostMapping("")
  public Mono<Category> create(@RequestBody Category category) {
    log.info("Creating a category, {}", category.toString());
    return categoryService
        .create(category)
        .doOnError(
            error ->
                log.info(
                    "Error when create Category. Category={}, Error={}",
                    category.toString(),
                    error.getMessage()));
  }

  @PutMapping("")
  public Mono<Category> update(@RequestBody Category category) {
    log.info("Updating a category, {}", category.toString());
    return categoryService
        .edit(category)
        .doOnError(
            error ->
                log.info(
                    "Error when update Category. Category={}, Error={}",
                    category.toString(),
                    error.getMessage()));
  }
}
