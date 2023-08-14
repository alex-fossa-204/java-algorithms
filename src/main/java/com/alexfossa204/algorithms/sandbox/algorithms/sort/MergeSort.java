package com.alexfossa204.algorithms.sandbox.algorithms.sort;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Исходный массив:");
        printArray(arr);

        mergeSort(arr);

        System.out.println("Отсортированный массив:");
        printArray(arr);
    }

    public static void mergeSort(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return; // базовый случай: если массив имеет 1 элемент или пустой, то считается отсортированным
        }

        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        // Заполняем подмассивы
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = arr[i];
        }

        // Рекурсивно сортируем оба подмассива
        mergeSort(left);
        mergeSort(right);

        // Слияние отсортированных подмассивов
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int nL = left.length;
        int nR = right.length;
        int i = 0, j = 0, k = 0;

        while (i < nL && j < nR) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы, если они есть
        while (i < nL) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < nR) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
