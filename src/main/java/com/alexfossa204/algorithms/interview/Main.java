package com.alexfossa204.algorithms.interview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
public class Main {
    /**
     * Необходимо сопоставить имена пользователей (username) их ролям (roles)
     * 1) Итоговый результат: структура данных String - List<Role>
     */
    public static void main(String[] args) throws InterruptedException {
        var users = List.of(
                User.of("jerry-Smith_43", "Jerry", "Smith", LocalDateTime.of(2020, 7, 23, 17, 30), List.of(Role.of("USER", List.of("READ", "CREATE", "UPDATE")))),
                User.of("rick-sunches-the-best-15", "Rick", "Sunches", LocalDateTime.of(2020, 7, 23, 17, 30), List.of(Role.of("ADMIN", List.of("READ", "CREATE", "UPDATE", "DELETE")))),
                User.of("mortyEvil", "Morty", "Smith Evil", LocalDateTime.of(2021, 8, 17, 20, 30), List.of(Role.of("ADMIN", List.of("READ", "CREATE", "UPDATE", "DELETE"))))
        );
    }

    @AllArgsConstructor(staticName = "of")
    @Data
    public static class User {

        private String username;

        private String firstName;

        private String lastName;

        private LocalDateTime registrationDate;

        private List<Role> roles;

    }

    @AllArgsConstructor(staticName = "of")
    @Data
    public static class Role {

        private String roleName;

        private List<String> privileges;

    }
}
