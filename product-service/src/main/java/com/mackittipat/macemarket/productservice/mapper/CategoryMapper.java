package com.mackittipat.macemarket.productservice.mapper;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.dto.ParentCategoryDto;
import com.mackittipat.macemarket.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

  @Autowired

  String CATEGORY_SEPARATOR = "|";

  Category dtoToEntity(CategoryDto car);

  CategoryDto entityToDto(Category category);

  default ParentCategoryDto entityToParentCategoryDto(Category category) {
    String value = "";
    String displayName = "";
    if (category.getLevel() == 0) {
      value = category.getId();
      displayName = category.getName();
    } else if (category.getLevel() == 1) {
      value = category.getId();
      displayName = category.getParentLevel0().getName() + CATEGORY_SEPARATOR + category.getName();
    }
    return ParentCategoryDto.builder()
            .id(category.getId())
            .name(category.getName())
            .value(value)
            .displayName(displayName)
            .build();
  }


}
