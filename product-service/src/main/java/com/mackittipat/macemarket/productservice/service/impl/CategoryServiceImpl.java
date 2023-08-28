package com.mackittipat.macemarket.productservice.service.impl;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.dto.ParentCategoryDto;
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
import java.util.Arrays;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryRepo categoryRepo;

  @Autowired private CategoryMapper categoryMapper;

  @Override
  public Mono<CategoryDto> findById(String id) {
    return categoryRepo
        .findById(id)
        .map(category -> categoryMapper.entityToDto(category))
        .flatMap(
            category -> {
              if ("1".equals(category.getLevel())) {
                // If category is level 1 then find parentCategory level 0
                return categoryRepo
                    .findById(category.getParentLevel0().getId())
                    .map(
                        parentCategoryL0 -> {
                          category.setParentLevel0(categoryMapper.entityToDto(parentCategoryL0));
                          return category;
                        });

              } else if ("2".equals(category.getLevel())) {
                // If category is level 2 then find parentCategory level 0 and 1
                return categoryRepo
                    .findAllById(
                        Arrays.asList(
                            category.getParentLevel0().getId(), category.getParentLevel1().getId()))
                    .map(
                        parentCategory -> {
                          if ("0".equals(category.getLevel())) {
                            category.setParentLevel0(categoryMapper.entityToDto(parentCategory));
                            category.getParentLevel0().setParentLevel0(null);
                          }
                          if ("1".equals(category.getLevel())) {
                            category.setParentLevel1(categoryMapper.entityToDto(parentCategory));
                            category.getParentLevel0().setParentLevel0(null);
                            category.getParentLevel1().setParentLevel0(null);
                            category.getParentLevel1().setParentLevel1(null);
                          }
                          return category;
                        })
                    .last();
              }
              return Mono.just(category);
            });
  }

  @Override
  public Flux<CategoryDto> findAll() {
    return categoryRepo
        .findAll(Sort.by(Sort.Direction.ASC, "name"))
        .map(category -> categoryMapper.entityToDto(category));
  }

  @Override
  public Flux<ParentCategoryDto> findAllParent() {
    return categoryRepo
        .findAllParent(Sort.by(Sort.Direction.ASC, "name"))
        .map(category -> categoryMapper.entityToParentCategoryDto(category));
  }

  @Override
  public Mono<CategoryDto> create(CategoryDto categoryDto) {
    categoryDto.setId(categoryDto.getName().replaceAll(" ", "").toLowerCase());
    categoryDto.setCreatedDateTime(LocalDateTime.now());
    categoryDto.setUpdatedDatetime(LocalDateTime.now());
    categoryDto.setActive("true");

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

  @Override
  public Mono<Void> delete(String id) {
    return categoryRepo.deleteById(id);
  }
}
