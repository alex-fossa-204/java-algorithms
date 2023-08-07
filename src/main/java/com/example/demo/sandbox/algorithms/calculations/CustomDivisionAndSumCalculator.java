package com.example.demo.sandbox.algorithms.calculations;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class CustomDivisionAndSumCalculator {

    public static void main(String[] args) {
        var calculationCondition = calculateDivisionInRangeByPredicateConditions(0, 1000,
                item -> item % 3 == 0,
                item -> item % 5 != 0,
                item -> isInnerSumLessThenCondition(item, 10)
        );
        calculationCondition.forEach(item -> log.info("Резльтат: res = {}", item));
    }

    static List<Integer> calculateDivisionInRangeByPredicateConditions(int leftBorder, int rightBorder, Predicate<Integer>... predicates) {
        return IntStream.range(leftBorder, rightBorder)
                .boxed()
                .filter(Stream.of(predicates).reduce(Predicate::and).orElse(predicate -> true))
                .collect(Collectors.toList());
    }

    static boolean isInnerSumLessThenCondition(Integer currentInteger, Integer sumCondition) {
        return splitIntegerToArray(currentInteger).stream()
                .collect(Collectors.summingInt(item -> item)) < sumCondition;
    }

    static List<Integer> splitIntegerToArray(Integer splittableInteger) {
        var splitterResult = new ArrayList<Integer>();
        while (splittableInteger != 0) {
            splitterResult.add(splittableInteger % 10);
            splittableInteger = splittableInteger / 10;
        }
        return splitterResult;
    }

}
