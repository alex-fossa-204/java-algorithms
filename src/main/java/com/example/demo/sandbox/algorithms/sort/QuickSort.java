package com.example.demo.sandbox.algorithms.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class QuickSort {

    public static void main(String[] args) {
        int[] array1 = {2, 5, 3, 7, 4, 8, 6, 9, 0, 2, 4, 3, 6, 4, 5, 7, 9, 8, 0, 5, 3, 5};
        log.info("Current array: {}", Arrays.toString(array1));

        quickSort(array1, 0, array1.length - 1);
        log.info("Sorted array: {}", Arrays.toString(array1));
    }

    public static void quickSort(int[] incomingArray, int leftBorder, int rightBorder) {

        if(leftBorder < rightBorder) {
            int divideIndex = runDivide(incomingArray, leftBorder, rightBorder);

            quickSort(incomingArray, leftBorder, divideIndex - 1);
            quickSort(incomingArray, divideIndex, rightBorder);
        }
    }

    private static int runDivide(int[] incomingArray, int leftBorder, int rightBorder) {
        int divideIndex = incomingArray[leftBorder + (rightBorder - leftBorder) / 2];

        while (leftBorder <= rightBorder) {

            while (incomingArray[leftBorder] < divideIndex) {
                leftBorder++;
            }

            while ((incomingArray[rightBorder] > divideIndex)) {
                rightBorder--;
            }

            if (leftBorder <= rightBorder) {
                doSwap(incomingArray, rightBorder, leftBorder);

                leftBorder++;
                rightBorder--;
            }
        }
        return leftBorder;
    }

    private static void doSwap(int[] incomingArray, int swapIndex1, int swapIndex2) {
        int temp = incomingArray[swapIndex1];

        incomingArray[swapIndex1] = incomingArray[swapIndex2];
        incomingArray[swapIndex2] = temp;
    }

}
