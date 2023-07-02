package com.mackittipat.macemarket.productservice.controller;

import com.mackittipat.macemarket.productservice.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorController {

  @ExceptionHandler(Exception.class)
  ResponseEntity errorHandler(Exception ex) {
    return new ResponseEntity<>(
        ApiResponseDto.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
            .message(ex.getMessage())
            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
