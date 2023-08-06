package com.example.demo.stubFunctionality.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(staticName = "of")
@Data
public class User {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDateTime registrationDate;

    private List<Role> roles;

}
