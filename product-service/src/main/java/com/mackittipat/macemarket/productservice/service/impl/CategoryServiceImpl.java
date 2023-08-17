package com.mackittipat.macemarket.productservice.service.impl;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.mapper.CategoryMapper;
import com.mackittipat.macemarket.productservice.repos.CategoryRepo;
import com.mackittipat.macemarket.productservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
            .map(category -> categoryMapper.entityToDto(category));
  }

  @Override
  public Flux<CategoryDto> findAll() {
    return categoryRepo.findAll(Sort.by(Sort.Direction.ASC, "name"))
            .map(category -> categoryMapper.entityToDto(category));
  }

  @Override
  public Mono<CategoryDto> create(CategoryDto categoryDto) {
    categoryDto.setId(categoryDto.getName().replaceAll(" ", "").toLowerCase());
    categoryDto.setCreatedDateTime(LocalDateTime.now());
    categoryDto.setUpdatedDatetime(LocalDateTime.now());
    categoryDto.setActive(true);

    return categoryRepo
        .insert(categoryMapper.dtoToEntity(categoryDto))
        .map(category -> categoryMapper.entityToDto(category));
  }

  @Override
  public Mono<CategoryDto> update(CategoryDto categoryDto) {
    categoryDto.setUpdatedDatetime(LocalDateTime.now());

    return categoryRepo
        .save(categoryMapper.dtoToEntity(categoryDto))
        .map(category -> categoryMapper.entityToDto(category));
  }
}
