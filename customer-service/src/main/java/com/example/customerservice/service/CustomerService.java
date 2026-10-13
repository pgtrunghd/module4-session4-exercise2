package com.example.customerservice.service;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;
import com.example.customerservice.dto.LoginRequestDTO;

public interface CustomerService {
    CustomerResponseDTO register(CustomerRequestDTO request);

    CustomerResponseDTO getById(Long id);

    CustomerResponseDTO login(LoginRequestDTO request);
}
