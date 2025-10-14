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

// @Transactional annotation is perhaps not necessary at this point, in the future it will ensure consistency 
// when perhaps other services are also interacting with the user entity.
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    // Sorting by id in descending order to show the latest users first (more convenient for demo purposes)
    public Slice<UserDto> getUsersSlice(int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return repo.findBy(pageable).map(UserDto::fromUser);
    }

    @Cacheable(value = "users", key = "#id")
    public UserDto getById(Long id) {
        return repo.findById(id).map(UserDto::fromUser)
                .orElseThrow(() -> new NotFoundException("User %d not found".formatted(id)));
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
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

        return new CreatedResource<UserDto>(UserDto.fromUser(saved), location);
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public UserDto update(Long id, UserRequestDto req) {
        var user = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User %d not found".formatted(id)));
        
        validateUniqueEmail(req.email(), id);

        user.setName(req.name());
        user.setEmail(req.email());
        return UserDto.fromUser(repo.save(user));
    }

    @Transactional
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
