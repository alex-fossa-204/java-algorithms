package com.alexfossa204.algorithms.stubFunctionality.service;

import com.alexfossa204.algorithms.stubFunctionality.entity.CreditRating;
import com.alexfossa204.algorithms.stubFunctionality.entity.User;

import java.util.concurrent.CompletableFuture;

public class CreditRatingService {

    public CreditRating findUserCreditRating(User user) {
        return CreditRating.of("SUPER", user);
    }

    public CompletableFuture<CreditRating> findUserCreditRatingFuture(User user) {
        return CompletableFuture.supplyAsync(() -> findUserCreditRating(user));
    }

}
