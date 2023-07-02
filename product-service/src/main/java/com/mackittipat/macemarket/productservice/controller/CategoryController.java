package com.mackittipat.macemarket.productservice.controller;

import com.mackittipat.macemarket.productservice.dto.ApiResponseDto;
import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("{id}")
  public Mono<ApiResponseDto<CategoryDto>> create(@PathVariable String id) {
    return categoryService.findById(id)
            .map(catDto -> ApiResponseDto.<CategoryDto>builder().data(catDto).build());
  }


  @PostMapping("")
  public Mono<ApiResponseDto<CategoryDto>> create(@RequestBody CategoryDto categoryDto) {
    log.info("Creating a category, {}", categoryDto.toString());
    return categoryService
        .create(categoryDto)
        .map(catDto -> ApiResponseDto.<CategoryDto>builder().data(catDto).build())
        .doOnError(
            error ->
                log.info(
                    "Error when create Category. {}, Error={}",
                    categoryDto.toString(),
                    error.getMessage()));
  }

  @PutMapping("")
  public Mono<ApiResponseDto<CategoryDto>> update(@RequestBody CategoryDto categoryDto) {
    log.info("Updating a category, {}", categoryDto.toString());
    return categoryService
        .edit(categoryDto)
        .map(catDto -> ApiResponseDto.<CategoryDto>builder().data(catDto).build())
        .doOnError(
            error ->
                log.info(
                    "Error when update Category. Category={}, Error={}",
                    categoryDto.toString(),
                    error.getMessage()));
  }
}
