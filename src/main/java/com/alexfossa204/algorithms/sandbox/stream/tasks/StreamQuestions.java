package com.alexfossa204.algorithms.sandbox.stream.tasks;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamQuestions {

    public static void main(String[] args) {
        log.info("Задача 1: {}", streamTask_1());
        log.info("Задача 2: {}", streamTask_2());
        log.info("Задача 3: {}", streamTask_3());
        streamTask_4();
        streamTask_5();
    }

    // что будет вывыедено на экран?
    // Ответ: 1, 6, 4, 3, 1, 3, 4, 6 -> тк collect() завершает стрим полностью
    static List<Integer> streamTask_1() {
        System.out.println("Stream 1");
        var streamInt = Stream.of(1, 6, 4, 3);
        return streamInt.peek(item -> System.out.println("f -> " + item))
                .sorted()
                .peek(item -> System.out.println("s -> " + item))
                .collect(Collectors.toList());
    }

    // что будет вывыедено на экран?
    // Ответ: 1, 6, 4, 3, 1, 3 -> тк anyMatch() завершает стрим при первом же совпадении по предикату
    static Boolean streamTask_2() {
        System.out.println("Stream 2");
        var streamInt = Stream.of(1, 6, 4, 3);
        return streamInt.peek(item -> System.out.println("f -> " + item))
                .sorted()
                .peek(item -> System.out.println("s -> " + item))
                .anyMatch(item -> item.equals(3));
    }

    // что будет вывыедено на экран?
    // Ответ: 1, 6, 4, 3, 1, 3 -> тк noneMatch() завершает стрим при первом же совпадении по предикату
    static Boolean streamTask_3() {
        System.out.println("Stream 3");
        var streamInt = Stream.of(1, 6, 4, 3);
        return streamInt.peek(item -> System.out.println("f -> " + item))
                .sorted()
                .peek(item -> System.out.println("s -> " + item))
                .noneMatch(item -> item.equals(3));

    }

    //что будет выведено на экран
    // ответ тк мы используем stateful операцию sorted то теперь стрим должен контролировать состояние каждого объекта во время выполнения
    //    filter: one
    //    map: one
    //    filter: two
    //    map: two
    //    filter: three
    //    forEach: ONE
    //    forEach: TWO
    static void streamTask_4() {
        System.out.println("Stream 4");
        final List<String> list = List.of("one", "two", "three");
        list.stream()
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.length() <= 3;
                })
                .map(s1 -> {
                    System.out.println("map: " + s1);
                    return s1.toUpperCase();
                })
                .sorted()
                .forEach(x -> {
                    System.out.println("forEach: " + x);
                });
        System.out.println("\n");
    }

    //что будет выведено на экран
    // ответ тк мы используем stateful операцию sorted то теперь стрим должен контролировать состояние каждого объекта во время выполнения
    //    filter: one
    //    map: one
    //    forEach: ONE
    //    filter: two
    //    map: two
    //    forEach: TWO
    //    filter: three
    static void streamTask_5() {
        System.out.println("Stream 5");
        final List<String> list = List.of("one", "two", "three");
        list.stream()
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.length() <= 3;
                })
                .map(s1 -> {
                    System.out.println("map: " + s1);
                    return s1.toUpperCase();
                })
                .distinct()
                .forEach(x -> {
                    System.out.println("forEach: " + x);
                });
        System.out.println("\n");
    }

}
