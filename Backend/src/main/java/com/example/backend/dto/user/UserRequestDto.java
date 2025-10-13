package com.example.backend.dto.user;

import com.example.backend.model.users.User;

// For what we receive from the client
public record UserRequestDto(String name, String email) {
    public static UserRequestDto fromUser(User user) {
        return new UserRequestDto(user.getName(), user.getEmail());
    }
    public UserRequestDto(User user) {
        this(user.getName(), user.getEmail());
    }
}
