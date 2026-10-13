package com.example.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {
    @NotBlank(message = "fullName must not be blank")
    private String fullName;

    @NotBlank(message = "email must not be blank")
    @Email(message = "email is invalid")
    private String email;

    @NotBlank(message = "password must not be blank")
    private String password;
}
