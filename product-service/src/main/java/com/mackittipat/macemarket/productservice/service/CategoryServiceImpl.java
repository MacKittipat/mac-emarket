package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.entity.Category;
import com.mackittipat.macemarket.productservice.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryRepo categoryRepo;

  @Override
  public Mono<Category> create(Category category) {
    category.setCreatedDateTime(LocalDateTime.now());
    return categoryRepo.insert(category);
  }

  @Override
  public Mono<Category> edit(Category category) {
    category.setUpdatedDatetime(LocalDateTime.now());
    return categoryRepo.save(category);
  }

}
