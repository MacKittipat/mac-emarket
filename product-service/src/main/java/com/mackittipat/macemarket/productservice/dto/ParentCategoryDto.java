package com.mackittipat.macemarket.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentCategoryDto {
    private String id;
    private String name;
    private String value;
    private String displayName;
    private int level;
}
