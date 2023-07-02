package com.mackittipat.macemarket.productservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseModel {

  private String message;
  private String detail;
  private String code;
}
