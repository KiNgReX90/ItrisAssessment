package com.example.backend.service;

import com.example.backend.dto.user.UserDto;
import com.example.backend.dto.user.UserRequestDto;
import com.example.backend.dto.CreatedResource;
import com.example.backend.exception.BadRequestException;
import com.example.backend.exception.NotFoundException;
import com.example.backend.model.users.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// @Transactional annotation is perhaps not necessary at this point, in the future it will ensure consistency,
// when perhaps other services are also interacting with the user entity.
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    // No caching here, since it creates a lot of cache entries and cache is being evicted each time a user is created, updated or deleted.
    public Slice<UserDto> getUsersSlice(int page, int size) {
        // Controlling the pagination parameters to avoid potential performance issues.
        if (page < 0 || size <= 0 || size > 100) {
            throw new IllegalArgumentException("Invalid pagination parameters: %d, %d".formatted(page, size));
        }

        // Using Pageable for a more database friendly approach.
        var pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return repo.findBy(pageable).map(UserDto::new);
    }

    @Cacheable(value = "users", key = "#id")
    public UserDto getById(Long id) {
        return repo.findById(id).map(UserDto::new)
                .orElseThrow(() -> new NotFoundException("User %d not found".formatted(id)));
    }

    @Transactional
    public CreatedResource<UserDto> create(UserRequestDto req) {
        validateUniqueEmail(req.email(), null);
        
        var user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        var saved = repo.save(user);

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return new CreatedResource<UserDto>(new UserDto(saved), location);
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public UserDto update(Long id, UserRequestDto req) {
        var user = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User %d not found".formatted(id)));
        
        validateUniqueEmail(req.email(), id);

        user.setName(req.name());
        user.setEmail(req.email());
        return new UserDto(repo.save(user));
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
        else {
            throw new NotFoundException("User %d not found".formatted(id));
        }
    }

    // If a new user is created, we need to check if the email is already in use by another user.
    // If an existing user is updated, we need to check if the email that we got from the request is not already in use by another user.
    // And ofcourse we exclude the user that we are updating from the check.
    private void validateUniqueEmail(String email, Long excludeUserId) {
        boolean emailExists = excludeUserId == null 
            ? repo.existsByEmail(email)
            : repo.existsByEmailAndIdNot(email, excludeUserId);
            
        if (emailExists) {
            throw new BadRequestException("This email is already in use. Please use a different email.");
        }
    }
}
