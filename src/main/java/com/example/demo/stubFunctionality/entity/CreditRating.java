package com.example.demo.stubFunctionality.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(staticName = "of")
@Data
public class CreditRating {

    private String creditRating;

    private User user;

}
