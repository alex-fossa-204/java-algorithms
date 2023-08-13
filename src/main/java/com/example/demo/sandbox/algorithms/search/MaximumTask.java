package com.example.demo.sandbox.algorithms.search;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
class MaximumTask {

    public static void main(String[] args) {
        int[] array = new int[]{33, 45, 13, 45, 22, 17, 51, 13, 21, 1, 21};
        int secondMaximum = MaximumUtils.findSecondMaximum(array);
        log.info("Результат вычисления второго максимума: result = {}", secondMaximum);

        int [] anyMaximumArray = new int [] {1, 2, 3};
        Arrays.stream(anyMaximumArray)
                .forEach(maximum -> log.info("Результат вычисления произвольного максимума: maximum = {}, result = {}", maximum, MaximumUtils.findAnyMaximum(array, maximum)));
    }

    static class MaximumUtils {

        static int findAnyMaximum(int [] sourceArray, int targetMaximum) {
            if (targetMaximum <=0) {
                throw new IllegalArgumentException(String.format("Искомый максимум должен быть натуральным числом: targetMaximum = %d", targetMaximum));
            }
            if (sourceArray.length <= targetMaximum) {
                throw new IllegalArgumentException(String.format("Исходный массив имеет недостаточную длину для поиска %d-ого максимума: sourceArray.length = %d", targetMaximum, sourceArray.length));
            }
            return Arrays.stream(sourceArray)
                    .distinct()
                    .boxed()
                    .sorted((num1, num2) -> Integer.compare(num2, num1))
                    .skip(targetMaximum - 1L)
                    .findFirst().orElseThrow(() -> new RuntimeException("Что-то пошло не так..."));
        }
        static int findSecondMaximum(int [] sourceArray) {
            final var targetMaximum = 2;
            if (sourceArray.length <= targetMaximum) {
                throw new IllegalArgumentException(String.format("Исходный массив имеет недостаточную длину для поиска %d-ого максимума: sourceArray.length = %d", targetMaximum, sourceArray.length));
            }
            var maximalValue = Integer.MIN_VALUE;
            var secondMaximum = Integer.MIN_VALUE;
            for (int item : sourceArray) {
                var isCurrentValueMaximal = item > maximalValue;
                if (isCurrentValueMaximal) {
                    secondMaximum = maximalValue;
                    maximalValue = item;
                }
                if (!isCurrentValueMaximal && item > secondMaximum) {
                    secondMaximum = item;
                }
            }
            return secondMaximum;
        }

    }
}
