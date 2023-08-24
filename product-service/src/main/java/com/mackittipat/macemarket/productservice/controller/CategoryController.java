package com.mackittipat.macemarket.productservice.controller;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.dto.ParentCategoryDto;
import com.mackittipat.macemarket.productservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "categories")
public class CategoryController {

  @Autowired private CategoryService categoryService;

  @GetMapping("{id}")
  public Mono<ResponseEntity<CategoryDto>> findById(@PathVariable String id) {
    return categoryService
        .findById(id)
        .map(catDto -> ResponseEntity.status(HttpStatus.OK).body(catDto))
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @GetMapping()
  public Flux<CategoryDto> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("parents")
  public Flux<ParentCategoryDto> findAllParent() {
    return categoryService.findAllParent();
  }

  @PostMapping("")
  public Mono<ResponseEntity<CategoryDto>> create(@RequestBody CategoryDto categoryDto) {
    log.info("Creating a category, {}", categoryDto.toString());
    return categoryService
        .create(categoryDto)
        .map(catDto -> ResponseEntity.status(HttpStatus.OK).body(catDto))
        .doOnError(
            error ->
                log.info(
                    "Error when create Category. {}, Error={}",
                    categoryDto.toString(),
                    error.getMessage()));
  }

  @PutMapping("")
  public Mono<ResponseEntity<CategoryDto>> update(@RequestBody CategoryDto categoryDto) {
    log.info("Updating a category, {}", categoryDto.toString());
    return categoryService
        .update(categoryDto)
        .map(catDto -> ResponseEntity.status(HttpStatus.OK).body(catDto))
        .doOnError(
            error ->
                log.info(
                    "Error when update Category. {}, Error={}",
                    categoryDto.toString(),
                    error.getMessage()));
  }

  @DeleteMapping("{id}")
  public Mono<Void> delete(@PathVariable String id) {
    log.info("Deleting a category, {}", id);
    return categoryService.delete(id);
  }
}
