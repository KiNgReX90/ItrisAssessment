package com.example.backend.dto.user;

import com.example.backend.model.users.User;

// For what we send to the client
// Usage of a DTO to avoid exposing the internal representation of the user entity to the client.
public record UserDto(Long id, String name, String email) {
    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
