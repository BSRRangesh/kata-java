package com.online.bookstore.user.repository;

import com.online.bookstore.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);

    Optional<Object> findByEmail(String email);
}
