package com.mackittipat.macemarket.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

  @Id private String id;
  private String sku;
  private String name;
  private String description;
  private Double price;
  private Category category;
  @Field(value = "images")
  private List<String> imageList;
  private LocalDateTime createdDateTime = LocalDateTime.now();
  private LocalDateTime updatedDatetime;
}
