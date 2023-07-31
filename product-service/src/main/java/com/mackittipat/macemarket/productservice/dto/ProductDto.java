package com.mackittipat.macemarket.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

  @Id private String id;
  private String sku;
  private String name;
  private String description;
  private Double price;
  private CategoryDto category;
  private List<String> imageList;
  private LocalDateTime createdDateTime = LocalDateTime.now();
  private LocalDateTime updatedDatetime;
}
