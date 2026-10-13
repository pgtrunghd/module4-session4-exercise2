package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
}
