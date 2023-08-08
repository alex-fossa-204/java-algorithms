package com.example.demo.sandbox.algorithms.calculations;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Slf4j
public class CalculateIsPrimeValue {

    public static void main(String[] args) {
        log.info("Результат проверки числа на простоту: " + isPrimeValue(58));
    }

    static boolean isPrimeValue(long targetValue) {
        if (targetValue <= 1) return false;
        return LongStream.rangeClosed(2, (long) Math.sqrt(targetValue))
                .noneMatch(item -> targetValue % item == 0);
    }

}
