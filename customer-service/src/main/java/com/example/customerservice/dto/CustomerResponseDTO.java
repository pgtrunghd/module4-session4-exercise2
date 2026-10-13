package com.example.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private String fullName;
    private String email;
}
