package com.example.backend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Slice<User> findBy(Pageable pageable);
    
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}