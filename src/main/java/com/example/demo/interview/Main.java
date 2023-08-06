package com.example.demo.interview;

import com.example.demo.stubFunctionality.entity.Role;
import com.example.demo.stubFunctionality.entity.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var users = List.of(
                User.of("misterSmith14", "Jerry", "Smith", LocalDateTime.now(), List.of(Role.of("USER", List.of("READ", "CREATE", "UPDATE")))),
                User.of("rickTheBest", "Rick", "Sunches", LocalDateTime.now(), List.of(Role.of("ADMIN", List.of("READ", "CREATE", "UPDATE", "DELETE")))),
                User.of("mortyEvil", "Morty", "Smith Evil", LocalDateTime.now(), List.of(Role.of("ADMIN", List.of("READ", "CREATE", "UPDATE", "DELETE"))))
        );

        var usernamesToPrivileges = users.stream()
                .collect(Collectors.toMap(
                        User::getUsername,
                        user -> user.getRoles().stream()
                                .flatMap(role -> role.getPrivileges().stream())
                                .collect(Collectors.toList()),
                        (key, value) -> value,
                        ConcurrentHashMap::new
                ));

        usernamesToPrivileges.entrySet().forEach(System.out::println);

        var usernamesToPrivilegesConcurrentHashMap = users.stream()
                .collect(Collectors.toMap(
                        User::getUsername,
                        user -> user.getRoles().stream()
                                .flatMap(role -> role.getPrivileges().stream())
                                .toList(),
                        (key, value) -> value,
                        HashMap::new
                ));
        usernamesToPrivilegesConcurrentHashMap.entrySet()
                .forEach(System.out::println);


    }
}
