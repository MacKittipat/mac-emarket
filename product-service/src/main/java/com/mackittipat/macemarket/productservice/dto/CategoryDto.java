package com.mackittipat.macemarket.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

  private String id;
  private String name;
  private int level;
  private String description;
  private CategoryDto parentLevel0;
  private CategoryDto parentLevel1;
  private LocalDateTime createdDateTime;
  private LocalDateTime updatedDatetime;
  private boolean active;
}
