package com.mackittipat.macemarket.productservice.service.impl;

import com.mackittipat.macemarket.productservice.dto.ProductDto;
import com.mackittipat.macemarket.productservice.mapper.ProductMapper;
import com.mackittipat.macemarket.productservice.repos.ProductRepo;
import com.mackittipat.macemarket.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired private ProductRepo productRepo;

  @Autowired private ProductMapper productMapper;

  @Override
  public Mono<ProductDto> findById(String id) {
    return productRepo.findById(id).map(category -> productMapper.entityToDto(category));
  }

  @Override
  public Flux<ProductDto> findAll() {
    return productRepo.findAll().map(category -> productMapper.entityToDto(category));
  }

  @Override
  public Mono<ProductDto> create(ProductDto productDto) {
    productDto.setCreatedDateTime(LocalDateTime.now());

    return productRepo
        .insert(productMapper.dtoToEntity(productDto))
        .map(category -> productMapper.entityToDto(category));
  }

  @Override
  public Mono<ProductDto> update(ProductDto productDto) {
    productDto.setUpdatedDatetime(LocalDateTime.now());

    return productRepo
        .save(productMapper.dtoToEntity(productDto))
        .map(category -> productMapper.entityToDto(category));
  }
}
