package com.mackittipat.macemarket.productservice.dto;

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
  private String description;
  private List<CategoryDto> subCategories;
  private LocalDateTime createdDateTime;
  private LocalDateTime updatedDatetime;
  private boolean active;

  public void setName(String name) {
    this.name = name;
    this.id = name.replaceAll(" ", "").toLowerCase();
  }
}
