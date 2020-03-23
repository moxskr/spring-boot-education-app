package com.example.auth.repos;

import com.example.auth.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
