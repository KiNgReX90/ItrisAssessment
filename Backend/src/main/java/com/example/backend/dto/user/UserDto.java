package com.example.backend.dto.user;

import com.example.backend.model.users.User;

public record UserDto(Long id, String name, String email) {
    public static UserDto fromUser(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
