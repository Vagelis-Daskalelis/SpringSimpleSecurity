package com.tasks.vagelis.repository;

import com.tasks.vagelis.entities.Role;
import com.tasks.vagelis.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);
}
