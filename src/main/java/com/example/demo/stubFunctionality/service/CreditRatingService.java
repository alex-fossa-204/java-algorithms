package com.example.demo.stubFunctionality.service;

import com.example.demo.stubFunctionality.entity.CreditRating;
import com.example.demo.stubFunctionality.entity.User;

import java.util.concurrent.CompletableFuture;

public class CreditRatingService {

    public CreditRating findUserCreditRating(User user) {
        return CreditRating.of("SUPER", user);
    }

    public CompletableFuture<CreditRating> findUserCreditRatingFuture(User user) {
        return CompletableFuture.supplyAsync(() -> findUserCreditRating(user));
    }

}
