package com.example.demo.stubFunctionality.service;

import com.example.demo.stubFunctionality.entity.Role;
import com.example.demo.stubFunctionality.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserService {

    public User findUserByUsername(String username) {
        return User.of(username, "Rick", "Sunches", LocalDateTime.now(), List.of(Role.of("ADMIN", List.of("READ", "CREATE", "UPDATE", "DELETE"))));
    }

    public CompletableFuture<User> findUserByUsernameFuture(String username) {
        return CompletableFuture.supplyAsync(() -> findUserByUsername(username));
    }

}
