package com.example.backend.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleNotFoundExceptionTest() {
        NotFoundException exception = new NotFoundException("User not found");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleNotFoundException(exception);
        LocalDateTime timestamp = (LocalDateTime) response.getBody().get("timestamp");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo(404);
        assertThat(response.getBody().get("error")).isEqualTo("Not Found");
        assertThat(response.getBody().get("message")).isEqualTo("User not found");
        assertThat(timestamp).isAfter(LocalDateTime.now().minusSeconds(2));
        assertThat(timestamp).isBefore(LocalDateTime.now().plusSeconds(2));
    }

    @Test
    void handleBadRequestExceptionTest() {
        BadRequestException exception = new BadRequestException("Invalid email format");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleBadRequestException(exception);
        LocalDateTime timestamp = (LocalDateTime) response.getBody().get("timestamp");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo(400);
        assertThat(response.getBody().get("error")).isEqualTo("Bad Request");
        assertThat(response.getBody().get("message")).isEqualTo("Invalid email format");
        assertThat(timestamp).isAfter(LocalDateTime.now().minusSeconds(2));
        assertThat(timestamp).isBefore(LocalDateTime.now().plusSeconds(2));
    }

    @Test
    void handleIllegalArgumentExceptionTest() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid pagination parameters");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleIllegalArgumentException(exception);
        LocalDateTime timestamp = (LocalDateTime) response.getBody().get("timestamp");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo(400);
        assertThat(response.getBody().get("error")).isEqualTo("Bad Request");
        assertThat(response.getBody().get("message")).isEqualTo("Invalid pagination parameters");
        assertThat(timestamp).isAfter(LocalDateTime.now().minusSeconds(2));
        assertThat(timestamp).isBefore(LocalDateTime.now().plusSeconds(2));
    }

    @Test
    void handleInternalServerErrorTest() {
        Exception exception = new Exception("Internal server error");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleInternalServerError(exception);
        LocalDateTime timestamp = (LocalDateTime) response.getBody().get("timestamp");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("status")).isEqualTo(500);
        assertThat(response.getBody().get("error")).isEqualTo("Internal Server Error");
        assertThat(response.getBody().get("message")).isEqualTo("An internal server error occurred. Please try again later.");
        assertThat(timestamp).isAfter(LocalDateTime.now().minusSeconds(2));
        assertThat(timestamp).isBefore(LocalDateTime.now().plusSeconds(2));
    }
}

