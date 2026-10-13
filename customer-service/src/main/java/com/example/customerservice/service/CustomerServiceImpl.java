package com.example.customerservice.service;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;
import com.example.customerservice.dto.LoginRequestDTO;
import com.example.customerservice.entity.Customer;
import com.example.customerservice.exception.DuplicateEmailException;
import com.example.customerservice.exception.InvalidCredentialsException;
import com.example.customerservice.exception.ResourceNotFoundException;
import com.example.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponseDTO register(CustomerRequestDTO request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email " + request.getEmail() + " đã tồn tại!");
        }
        Customer customer = new Customer();
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        return toResponse(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng với ID " + id + " không tồn tại!"));
        return toResponse(customer);
    }

    @Override
    public CustomerResponseDTO login(LoginRequestDTO request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .filter(c -> passwordEncoder.matches(request.getPassword(), c.getPassword()))
                .orElseThrow(() -> new InvalidCredentialsException("email or password incorrect"));
        return toResponse(customer);
    }

    private CustomerResponseDTO toResponse(Customer c) {
        return new CustomerResponseDTO(c.getId(), c.getFullName(), c.getEmail());
    }
}
