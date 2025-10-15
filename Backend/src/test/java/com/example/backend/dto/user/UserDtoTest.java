package com.example.backend.dto.user;

import com.example.backend.model.users.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {

    private User testUser;
        
    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("John Doe");
        testUser.setEmail("john@example.com");
    }

    @Test
    void constructorTest() {
        UserDto result = new UserDto(3L, "Bob Wilson", "bob@example.com");

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(3L);
        assertThat(result.name()).isEqualTo("Bob Wilson");
        assertThat(result.email()).isEqualTo("bob@example.com");
    }

    @Test
    void constructorWithUserTest() {
        UserDto result = new UserDto(testUser);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.email()).isEqualTo("john@example.com");
    }
}

