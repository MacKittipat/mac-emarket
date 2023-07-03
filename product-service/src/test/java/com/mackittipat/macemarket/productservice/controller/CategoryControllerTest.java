package com.mackittipat.macemarket.productservice.controller;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.service.CategoryService;
import com.mackittipat.macemarket.productservice.service.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

// @SpringBootTest
// @ExtendWith(SpringExtension.class)

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CategoryController.class)
@Import(CategoryServiceImpl.class)
class CategoryControllerTest {

  @Autowired private WebTestClient webTestClient;

  @MockBean private CategoryService categoryService;

  @Test
  void findById() {
    CategoryDto categoryDto = CategoryDto.builder().name("Electronic").build();
    Mockito.when(categoryService.findById(Mockito.anyString())).thenReturn(Mono.just(categoryDto));

    webTestClient
        .get()
        .uri("/categories/beauty")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CategoryDto.class)
        .value(CategoryDto::getId, equalTo(categoryDto.getName().toLowerCase()))
        .value(CategoryDto::getName, equalTo(categoryDto.getName()));
  }

  @Test
  void findById2() {
    CategoryDto categoryDto = CategoryDto.builder().name("Electronic").build();
    Mockito.when(categoryService.findById(Mockito.anyString())).thenReturn(Mono.just(categoryDto));

    webTestClient
        .get()
        .uri("/categories/beauty")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.id")
        .isEqualTo(categoryDto.getId())
        .jsonPath("$.name")
        .isEqualTo(categoryDto.getName());
  }

  @Test
  void findByIdNotFound() {
    Mockito.when(categoryService.findById(Mockito.anyString())).thenReturn(Mono.empty());

    webTestClient
        .get()
        .uri("/categories/beauty")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isNoContent();
  }

  @Test
  void findAll() {
    Mockito.when(categoryService.findAll())
        .thenReturn(
            Flux.just(
                CategoryDto.builder().name("Electronic").build(),
                CategoryDto.builder().name("Beauty").build()));

    webTestClient
        .get()
        .uri("/categories")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(CategoryDto.class)
        .value(List::size, equalTo(2))
        .value(categoryDtoList -> categoryDtoList.get(0).getId(), equalTo("electronic"))
        .value(categoryDtoList -> categoryDtoList.get(0).getName(), equalTo("Electronic"))
        .value(categoryDtoList -> categoryDtoList.get(1).getId(), equalTo("beauty"))
        .value(categoryDtoList -> categoryDtoList.get(1).getName(), equalTo("Beauty"));
  }

  @Test
  void findAllNotFound() {
    Mockito.when(categoryService.findAll()).thenReturn(Flux.empty());

    webTestClient
        .get()
        .uri("/categories")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(CategoryDto.class)
        .value(List::size, equalTo(0));
  }

  @Test
  void create() {
    CategoryDto categoryDto = CategoryDto.builder().name("Electronic").build();
    Mockito.when(categoryService.create(Mockito.any(CategoryDto.class)))
        .thenReturn(Mono.just(categoryDto));

    webTestClient
        .post()
        .uri("/categories")
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(categoryDto)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CategoryDto.class)
        .value(CategoryDto::getId, equalTo(categoryDto.getName().toLowerCase()))
        .value(CategoryDto::getName, equalTo(categoryDto.getName()));
  }

  @Test
  void update() {
    CategoryDto categoryDto = CategoryDto.builder().name("Electronic").build();
    Mockito.when(categoryService.update(Mockito.any(CategoryDto.class)))
        .thenReturn(Mono.just(categoryDto));

    webTestClient
        .put()
        .uri("/categories")
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(CategoryDto.builder().name("Beauty").build())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CategoryDto.class)
        .value(CategoryDto::getId, equalTo(categoryDto.getName().toLowerCase()))
        .value(CategoryDto::getName, equalTo(categoryDto.getName()));
  }
}
