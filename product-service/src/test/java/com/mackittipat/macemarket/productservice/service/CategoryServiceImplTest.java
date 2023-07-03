package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.dto.CategoryDto;
import com.mackittipat.macemarket.productservice.entity.Category;
import com.mackittipat.macemarket.productservice.mapper.CategoryMapperImpl;
import com.mackittipat.macemarket.productservice.repos.CategoryRepo;
import com.mongodb.MongoWriteException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@Slf4j
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {

  @InjectMocks private CategoryServiceImpl categoryService;

  @Mock private CategoryRepo categoryRepo;

  @Spy private CategoryMapperImpl categoryMapper;

  @Test
  void findById() {
    Category category = Category.builder().name("Electronic").build();
    Mockito.when(categoryRepo.findById(Mockito.anyString())).thenReturn(Mono.just(category));

    Mono<CategoryDto> categoryDtoMono = categoryService.findById(category.getName().toLowerCase());

    StepVerifier.create(categoryDtoMono)
            .expectNextMatches(categoryDto -> categoryDto.getName().equals(category.getName()))
            .verifyComplete();
    Mockito.verify(categoryRepo, Mockito.times(1)).findById(Mockito.anyString());
  }

  @Test
  void findByIdNotFound() {
    Mockito.when(categoryRepo.findById(Mockito.anyString())).thenReturn(Mono.empty());

    Mono<CategoryDto> categoryDtoMono = categoryService.findById("category");

    StepVerifier.create(categoryDtoMono)
            .expectNextCount(0)
            .verifyComplete();
    Mockito.verify(categoryRepo, Mockito.times(1)).findById(Mockito.anyString());
  }

  @Test
  void findAll() {
    Flux<Category> categoryFlux =
        Flux.just(
            Category.builder().id("id1").name("Electronic").build(),
            Category.builder().id("id2").name("Beauty").build());
    Mockito.when(categoryRepo.findAll()).thenReturn(categoryFlux);

    Flux<CategoryDto> categoryDtoFlux = categoryService.findAll();

    StepVerifier.create(categoryDtoFlux)
            .expectNextMatches(categoryDto -> categoryDto.getName().equals("Electronic"))
            .expectNextMatches(categoryDto -> categoryDto.getName().equals("Beauty"))
            .verifyComplete();
    Mockito.verify(categoryRepo, Mockito.times(1)).findAll();
  }

  @Test
  void findAllNotFound() {
    Mockito.when(categoryRepo.findAll()).thenReturn(Flux.empty());

    Flux<CategoryDto> categoryDtoFlux = categoryService.findAll();

    StepVerifier.create(categoryDtoFlux)
            .expectNextCount(0)
            .verifyComplete();
    Mockito.verify(categoryRepo, Mockito.times(1)).findAll();
  }

  @Test
  void createSuccess() {
    Category category = Category.builder().name("Electronic").build();
    Mockito.when(categoryRepo.insert(Mockito.any(Category.class))).thenReturn(Mono.just(category));

    Mono<CategoryDto> categoryDtoMono =
        categoryService.create(categoryMapper.entityToDto(category));

    StepVerifier.create(categoryDtoMono)
        .expectNextMatches(categoryDto -> categoryDto.getName().equals(category.getName()))
        .verifyComplete();
    Mockito.verify(categoryRepo, Mockito.times(1)).insert(Mockito.any(Category.class));

    categoryDtoMono.subscribe(
        categoryDto -> Assertions.assertEquals(categoryDto.getName(), category.getName()));
  }

  @Test
  void createFail() {
    Category category = Category.builder().name("Electronic").build();
    Mockito.when(categoryRepo.insert(Mockito.any(Category.class)))
        .thenThrow(MongoWriteException.class);

    Assertions.assertThrows(
        MongoWriteException.class,
        () -> {
          categoryService.create(categoryMapper.entityToDto(category));
        });
  }

  @Test
  void edit() {
    Category category =
        Category.builder().name("Electronic").createdDateTime(LocalDateTime.now()).build();

    Mockito.when(categoryRepo.save(Mockito.any(Category.class))).thenReturn(Mono.just(category));
    Mono<CategoryDto> categoryDtoMono = categoryService.edit(categoryMapper.entityToDto(category));

    StepVerifier.create(categoryDtoMono)
        .expectNextMatches(categoryDto -> categoryDto.getName().equals(category.getName()))
        .verifyComplete();
    Mockito.verify(categoryRepo, Mockito.times(1)).save(Mockito.any(Category.class));
  }
}
