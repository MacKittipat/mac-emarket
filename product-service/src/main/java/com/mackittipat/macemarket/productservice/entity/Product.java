package com.mackittipat.macemarket.productservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
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

  public Product(String sku, String name, String description, Double price) {
    this.sku = sku;
    this.name = name;
    this.description = description;
    this.price = price;
  }
}
