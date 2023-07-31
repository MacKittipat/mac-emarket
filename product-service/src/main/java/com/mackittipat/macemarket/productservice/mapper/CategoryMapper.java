package com.mackittipat.macemarket.productservice.mapper;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

  Category dtoToEntity(CategoryDto car);

  CategoryDto entityToDto(Category category);
}
