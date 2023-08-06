package com.example.demo.stubFunctionality.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor(staticName = "of")
@Data
public class Role {

    private String roleName;

    private List<String> privileges;

}
