package com.mackittipat.macemarket.productservice.service.impl;

import com.mackittipat.macemarket.productservice.dto.ProductDto;
import com.mackittipat.macemarket.productservice.dto.ProductSearchDto;
import com.mackittipat.macemarket.productservice.mapper.ProductMapper;
import com.mackittipat.macemarket.productservice.repos.ProductRepo;
import com.mackittipat.macemarket.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    return productRepo.findById(id).map(product -> productMapper.entityToDto(product));
  }

  @Override
  public Flux<ProductDto> findAll() {
    return productRepo.findAll().map(product -> productMapper.entityToDto(product));
  }

  @Override
  public Flux<ProductDto> search(ProductSearchDto productSearchDto) {
    Query query = new Query();

    if (StringUtils.isNoneBlank(productSearchDto.getName())) {
      query.addCriteria(Criteria.where("name").is(productSearchDto.getName()));
    }

    if (StringUtils.isNoneBlank(productSearchDto.getSku())) {
      query.addCriteria(Criteria.where("sku").is(productSearchDto.getSku()));
    }

    if (StringUtils.isNoneBlank(productSearchDto.getPrice())) {
      query.addCriteria(Criteria.where("price").is(Double.valueOf(productSearchDto.getPrice())));
    }

    log.info("Searching with query {}", query.toString());
    return productRepo.search(query).map(product -> productMapper.entityToDto(product));
  }

  @Override
  public Mono<ProductDto> create(ProductDto productDto) {
    productDto.setCreatedDateTime(LocalDateTime.now());

    return productRepo
        .insert(productMapper.dtoToEntity(productDto))
        .map(product -> productMapper.entityToDto(product));
  }

  @Override
  public Mono<ProductDto> update(ProductDto productDto) {
    productDto.setUpdatedDatetime(LocalDateTime.now());

    return productRepo
        .save(productMapper.dtoToEntity(productDto))
        .map(product -> productMapper.entityToDto(product));
  }

  @Override
  public Mono<Void> delete(String id) {
    return productRepo.deleteById(id);
  }
}
