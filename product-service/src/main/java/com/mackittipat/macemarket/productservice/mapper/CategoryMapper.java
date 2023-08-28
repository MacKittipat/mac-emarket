package com.mackittipat.macemarket.productservice.mapper;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.dto.ParentCategoryDto;
import com.mackittipat.macemarket.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

  String CATEGORY_SEPARATOR = "|";

  Category dtoToEntity(CategoryDto car);

  CategoryDto entityToDto(Category category);

  default ParentCategoryDto entityToParentCategoryDto(Category category) {
    String value = "";
    String displayName = "";
    if ("0".equals(category.getLevel())) {
      value = category.getId();
      displayName = category.getName();
    } else if ("1".equals(category.getLevel())) {
      value = category.getId();
      displayName = category.getParentLevel0().getName() + CATEGORY_SEPARATOR + category.getName();
    }
    return ParentCategoryDto.builder()
        .id(category.getId())
        .name(category.getName())
        .value(value)
        .displayName(displayName)
        .level(category.getLevel())
        .build();
  }
}
