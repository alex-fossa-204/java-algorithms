package com.alexfossa204.algorithms.sandbox.algorithms.search;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class FindRepeatedValues {

    public static void main(String[] args) {
        var intArray = new int[]{23, 17, 14, 45, 22, 56, 17, 82, 45};

        var repeatCondition = 2;
        var calculationResult = calculateRepeatedValues(intArray, repeatCondition);
        calculationResult.entrySet()
                .forEach(item -> log.info("Число {} повторяется {} раз", item.getKey(), item.getValue()));
    }

    static Map<Integer, Integer> calculateRepeatedValues(int [] incomingArray, int repeatConditionCounter) {
        final var repeatHashMap = new HashMap<Integer, Integer>();
        final var baseCounter = 1;
        for (int item : incomingArray) {
            var isMapContainsKey = repeatHashMap.containsKey(item);
            if (!isMapContainsKey) {
                repeatHashMap.put(item, baseCounter);
            }
            if (isMapContainsKey) {
                repeatHashMap.put(item, repeatHashMap.get(item) + 1);
            }
        }
        return repeatHashMap.entrySet().stream()
                .filter(entry -> entry.getValue() >= repeatConditionCounter)
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue()
                ));
    }

}
