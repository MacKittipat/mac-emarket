package com.mackittipat.macemarket.productservice.dto;

import com.mackittipat.macemarket.productservice.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

  private String id;
  private String name;
  private List<Category> subCategories;
  private LocalDateTime createdDateTime;
  private LocalDateTime updatedDatetime;
}
