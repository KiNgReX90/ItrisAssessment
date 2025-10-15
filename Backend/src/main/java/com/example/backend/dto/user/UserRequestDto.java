package com.example.backend.dto.user;

import com.example.backend.model.users.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String name,
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(min = 1, max = 255, message = "Email must be between 1 and 255 characters")
    String email
) {
    public UserRequestDto(User user) {
        this(user.getName(), user.getEmail());
    }
}
