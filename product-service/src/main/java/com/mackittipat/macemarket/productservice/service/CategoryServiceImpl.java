package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.entity.Category;
import com.mackittipat.macemarket.productservice.mapper.CategoryMapper;
import com.mackittipat.macemarket.productservice.repos.CategoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryRepo categoryRepo;

  @Autowired private CategoryMapper categoryMapper;

  @Override
  public Mono<CategoryDto> findById(String id) {
    return categoryRepo.findById(id)
            .defaultIfEmpty(Category.builder().build())
            .map(category -> categoryMapper.entityToDto(category));
  }

  @Override
  public Mono<CategoryDto> create(CategoryDto categoryDto) {
    categoryDto.setCreatedDateTime(LocalDateTime.now());

    return categoryRepo
        .insert(categoryMapper.dtoToEntity(categoryDto))
        .map(category -> categoryMapper.entityToDto(category));
  }

  @Override
  public Mono<CategoryDto> edit(CategoryDto categoryDto) {
    categoryDto.setUpdatedDatetime(LocalDateTime.now());

    return categoryRepo
        .save(categoryMapper.dtoToEntity(categoryDto))
        .map(category -> categoryMapper.entityToDto(category));
  }
}
