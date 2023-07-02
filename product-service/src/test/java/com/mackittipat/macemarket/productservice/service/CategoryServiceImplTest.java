package com.mackittipat.macemarket.productservice.service;

import com.mackittipat.macemarket.productservice.entity.Category;
import com.mackittipat.macemarket.productservice.repos.CategoryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {

  @InjectMocks private CategoryServiceImpl categoryService;

  @Mock private CategoryRepo categoryRepo;

  @Test
  void create() {
    Category category = Category.builder().name("Electronic").build();
    Mockito.when(categoryRepo.insert(Mockito.any(Category.class))).thenReturn(Mono.just(category));
    Mono<Category> categoryMono = categoryService.create(category);

    StepVerifier.create(categoryMono).expectNextCount(1).verifyComplete();
    Mockito.verify(categoryRepo, Mockito.times(1)).insert(Mockito.any(Category.class));

    Assertions.assertNotNull(category.getName());
    Assertions.assertNotNull(category.getCreatedDateTime());
    Assertions.assertNull(category.getUpdatedDatetime());
    Assertions.assertNull(category.getSubCategories());
  }

  @Test
  void edit() {
    Category category =
        Category.builder().name("Electronic").createdDateTime(LocalDateTime.now()).build();
    Mockito.when(categoryRepo.save(Mockito.any(Category.class))).thenReturn(Mono.just(category));
    Mono<Category> categoryMono = categoryService.edit(category);

    StepVerifier.create(categoryMono).expectNextCount(1).verifyComplete();
    Mockito.verify(categoryRepo, Mockito.times(1)).save(Mockito.any(Category.class));

    Assertions.assertNotNull(category.getName());
    Assertions.assertNotNull(category.getUpdatedDatetime());
    Assertions.assertNotNull(category.getCreatedDateTime());
    Assertions.assertNull(category.getSubCategories());
  }
}
