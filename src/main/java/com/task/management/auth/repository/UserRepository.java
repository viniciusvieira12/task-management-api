package com.task.management.auth.repository;

import com.task.management.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//Search for a user by username and return optional
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
