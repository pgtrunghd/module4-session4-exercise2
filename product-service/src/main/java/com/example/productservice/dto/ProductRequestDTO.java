package com.example.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "price is required")
    @Min(value = 1, message = "price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "stockQuantity is required")
    @Min(value = 0, message = "stockQuantity must not be negative")
    private Integer stockQuantity;
}
