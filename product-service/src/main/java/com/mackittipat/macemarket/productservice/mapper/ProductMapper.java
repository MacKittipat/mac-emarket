package com.mackittipat.macemarket.productservice.mapper;

import com.mackittipat.macemarket.productservice.dto.ProductDto;
import com.mackittipat.macemarket.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

  Product dtoToEntity(ProductDto car);

  ProductDto entityToDto(Product category);
}
