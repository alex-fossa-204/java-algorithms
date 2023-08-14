package com.alexfossa204.algorithms.sandbox.algorithms.search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindNearestValue {

    public static void main(String[] args) {
        var intArray = new Integer [] {3, 5, 18, 19, 9, 11, 13, 16, 19, 22, 25};

        var resultValue = findNearestValue(21, intArray);
        log.info("Текущее ближайшее значение: result = {}", resultValue);
    }

    static int findNearestValue(Integer targetValue, Integer [] targetArray) {
        var nearestValue = targetArray[0];
        var baseDelta = Math.abs(nearestValue - targetValue);
        for (int i = 1; i < targetArray.length; i++) {
            var currentValue = targetArray[i];
            var currentDelta = Math.abs(currentValue - targetValue);
            if (currentDelta < baseDelta) {
                nearestValue = targetArray[i];
                baseDelta = currentDelta;
            }
        }
        return nearestValue;
    }

}
