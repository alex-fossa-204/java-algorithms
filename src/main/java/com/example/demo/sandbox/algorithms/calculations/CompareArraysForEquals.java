package com.example.demo.sandbox.algorithms.calculations;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class CompareArraysForEquals {

    public static void main(String[] args) {
        int[] array1 = new int[]{3, 5, 7, 8};
        int[] array2 = new int[]{3, 5, 7, 8};

        log.info("Результат сравнения массивов: {}", compareArraysNaive(array1, array2));

        log.info("Результат сравнения массивов: {}", compareArraysUtil(array1, array2));
    }

    static boolean compareArraysNaive(int[] targetArray1, int[] targetArray2) {
        if (targetArray1.length != targetArray2.length) {
            throw new IllegalArgumentException("Массивы не равны по длине");
        }
        if (Arrays.hashCode(targetArray1) != Arrays.hashCode(targetArray2)) {
            log.info("Массивы гарантированно не равны: targetArray1.hashcode != targetArray2.hashcode");
            return false;
        }
        var arrayLength = targetArray1.length;
        for (int i = 0; i < arrayLength - 1; i++) {
            var isItemsEqual = targetArray1[i] == targetArray2[i];
            if (!isItemsEqual) {
                log.info("Массивы не равны");
                return false;
            }
        }
        return true;
    }

    static boolean compareArraysUtil(int[] targetArray1, int[] targetArray2) {
        return Arrays.equals(targetArray1, targetArray2);
    }

}
