package com.alexfossa204.algorithms.sandbox.algorithms.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class CountSort {

    public static void main(String[] args) {
        int[] array1 = {2, 5, 3, 7, 4, 8, 6, 9, 0, 2, 4, 3, 6, 4, 5, 7, 9, 8, 0, 5, 3, 5};
        log.info("Current array: {}", Arrays.toString(array1));

        countSort(array1);
        log.info("Sorted array: {}", Arrays.toString(array1));
    }

    public static void countSort(int[] incomingArray) {
        final int MAX_VALUE = 100;
        int[] count = new int[MAX_VALUE];

        for (int i = 0; i < incomingArray.length; i++) {
            count[incomingArray[i]] = count[incomingArray[i]] + 1;
        }

        int arrIndex = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                incomingArray[arrIndex] = i;
                arrIndex++;
            }
        }
    }

}
