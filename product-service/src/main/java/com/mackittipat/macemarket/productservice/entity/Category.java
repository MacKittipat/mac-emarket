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
@Document(collection = "categories")
public class Category {

  @Id private String id;
  private String name;
  @Field(value = "subCategories")
  private List<Category> subCategories;
  private LocalDateTime createdDateTime;
  private LocalDateTime updatedDatetime;
}
