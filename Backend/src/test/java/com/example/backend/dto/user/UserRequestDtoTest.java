package com.example.backend.dto.user;

import com.example.backend.model.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRequestDtoTest {

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
        UserRequestDto result = new UserRequestDto("Jane Smith", "jane@example.com");

        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo("Jane Smith");
        assertThat(result.email()).isEqualTo("jane@example.com");
    }

    @Test
    void constructorWithUserTest() {
        UserRequestDto result = new UserRequestDto(testUser);
        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.email()).isEqualTo("john@example.com");
    }
}

