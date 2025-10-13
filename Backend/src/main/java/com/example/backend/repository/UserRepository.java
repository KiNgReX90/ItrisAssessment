package com.example.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}