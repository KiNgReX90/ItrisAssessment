package com.example.backend.controller;

import com.example.backend.dto.CreatedResource;
import com.example.backend.dto.user.UserDto;
import com.example.backend.dto.user.UserRequestDto;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // For development purposes, in production this should be changed accordingly.
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Slice<UserDto>> getUsersSlice(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "25") int size
    ) {
        return ResponseEntity.ok(userService.getUsersSlice(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequestDto request) {
        CreatedResource<UserDto> created = userService.create(request);
        return ResponseEntity
                .created(created.location())
                .body(created.dto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}