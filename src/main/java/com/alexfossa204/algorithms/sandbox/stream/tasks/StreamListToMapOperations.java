package com.alexfossa204.algorithms.sandbox.stream.tasks;

import com.alexfossa204.algorithms.stubFunctionality.entity.Role;
import com.alexfossa204.algorithms.stubFunctionality.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StreamListToMapOperations {

    public static void main(String[] args) {
        var users = List.of(
                User.of("misterSmith14", "Jerry", "Smith", LocalDateTime.of(2020, 7, 23, 17, 30), List.of(Role.of("USER", List.of("READ", "CREATE", "UPDATE")))),
                User.of("rickTheBest", "Rick", "Sunches", LocalDateTime.of(2020, 7, 23, 17, 30), List.of(Role.of("ADMIN", List.of("READ", "CREATE", "UPDATE", "DELETE")))),
                User.of("mortyEvil", "Morty", "Smith Evil", LocalDateTime.of(2021, 8, 17, 20, 30), List.of(Role.of("ADMIN", List.of("READ", "CREATE", "UPDATE", "DELETE"))))
        );
        mapUserToUsernamePrivilegesWithFlatMap(users);
        groupUsersByRegistrationDate(users);

    }

    static void mapUserToUsernamePrivilegesWithFlatMap(List<User> users) {
        var usernameUserMap = users.stream()
                .collect(Collectors.toMap(
                        user -> user.getUsername(),
                        user -> user.getRoles().stream()
                                .flatMap(role -> role.getPrivileges().stream())
                                .collect(Collectors.toList()))
                );
        usernameUserMap.entrySet()
                .forEach(item -> log.info("mappedItem: {}", item));
    }

    static void groupUsersByRegistrationDate(List<User> users) {
        var groupedUsers = users.stream()
                .collect(Collectors.groupingBy(
                        user -> user.getRegistrationDate()
                ));

        groupedUsers.entrySet()
                .forEach(item -> log.info("groupedItem: {}", item));
    }

}
