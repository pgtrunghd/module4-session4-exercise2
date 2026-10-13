package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Long customerId;
    private Long productId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
}
