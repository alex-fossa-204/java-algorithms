package com.alexfossa204.algorithms.sandbox.algorithms.search;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
class BinarySearchTask {

    public static void main(String[] args) {
        var intArray = new int[]{1,2,3,4};

        log.info("Результат поиска imperative: result = {}", SearchUtils.binarySearchImperative(Arrays.stream(intArray).sorted().toArray(), 4));

        log.info("Результат поиска c Stream API: result = {}", SearchUtils.searchWithStreamApi(Arrays.stream(intArray).sorted().toArray(), 4));

    }

    static class SearchUtils {

        static int binarySearchImperative(int[] array, int searchTarget) {
            if (array.length == 0) {
                throw new IllegalStateException("Массив не может быть пустым");
            }
            var leftItem = 0;
            var rightItem = array.length - 1;
            while (leftItem <= rightItem) {
                int medium = leftItem + (rightItem - leftItem) / 2;
                if (array[medium] == searchTarget) {
                    return medium;
                }
                if (array[medium] < searchTarget) {
                    leftItem = medium + 1;
                }
                if (array[medium] > searchTarget) {
                    rightItem = medium - 1;
                }
            }
            return Integer.MIN_VALUE;
        }

        static int searchWithStreamApi(int[] sortedArray, int searchTarget) {
            return IntStream.range(0, sortedArray.length)
                    .filter(item -> sortedArray[item] == searchTarget)
                    .findFirst()
                    .orElseThrow();
        }

        static Map<Integer, Integer> findRepeatedValueMap(int[] array) {
            var map = new HashMap<Integer, Integer>();
            for (int item : array) {
                var isMapContainsKey = map.containsKey(item);
                if (isMapContainsKey) {
                    var counter = map.get(item);
                    map.put(item, counter + 1);
                }
                if (!isMapContainsKey) {
                    map.put(item, 1);
                }
            }
            return map;
        }

    }

}
