package com.example.demo.sandbox.algorithms.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class SelectionSort {

    public static void main(String[] args) {
        int[] array1 = {2, 5, 3, 7, 4, 8, 6, 9, 0, 2, 4, 3, 6, 4, 5, 7, 9, 8, 0, 5, 3, 5};
        log.info("Current array: {}", Arrays.toString(array1));

        sortSelection_v1(array1);
        log.info("Sorted array: {}", Arrays.toString(array1));
    }

    public static void sortSelection_v1(int[] incomingIntArray) {

        for (int step = 0; step < incomingIntArray.length; step++) {
            int minValueIndex = searchMinimalValueIndex(incomingIntArray, step); //find minimal value index

            int tempValue = incomingIntArray[step]; //save current value
            incomingIntArray[step] = incomingIntArray[minValueIndex]; //swap current value and minimal value

            incomingIntArray[minValueIndex] = tempValue;

        }
    }

    public static int searchMinimalValueIndex(int[] incomingArray, int startIndex) {
        int minimalValueIndex = startIndex;
        int minimalValue = incomingArray[startIndex];

        for (int i = startIndex + 1; i < incomingArray.length; i++) {
            if (incomingArray[i] < minimalValue) {
                minimalValue = incomingArray[i];
                minimalValueIndex = i;
            }
        }
        return minimalValueIndex;
    }

}
