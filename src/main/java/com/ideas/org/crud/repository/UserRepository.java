package com.ideas.org.crud.repository;

import com.ideas.org.crud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsernameIgnoreCase(String userName);

    Optional<User> findByUsername(String username);
}
