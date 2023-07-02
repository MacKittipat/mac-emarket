package com.mackittipat.macemarket.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<R> {

  private String status;
  private String message;
  private R data;
}
