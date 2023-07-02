package com.mackittipat.macemarket.productservice.mapper;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  Category dtoToEntity(CategoryDto car);

  @Mapping(source = "name", target = "name")
  CategoryDto entityToDto(Category category);
}
