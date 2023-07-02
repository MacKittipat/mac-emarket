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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@Slf4j
// @SpringBootTest
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {

  @InjectMocks private CategoryServiceImpl categoryService;

  @Mock private CategoryRepo categoryRepo;

  @Spy private CategoryMapperImpl categoryMapper;

  @Test
  void findById() {

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
