package com.mackittipat.macemarket.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class Category {

  @Id private String id;
  private String name;
  private String level;
  private String description;
  private Category parentLevel0;
  private Category parentLevel1;
  private LocalDateTime createdDateTime;
  private LocalDateTime updatedDatetime;
  private boolean active;
}
