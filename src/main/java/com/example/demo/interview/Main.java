package com.example.demo.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var streamInt = Stream.of(1, 6, 4, 3);
        streamInt.peek(item -> System.out.println("f -> " + item))
                .sorted()
                .peek(item -> System.out.println("s -> " + item))
                .collect(Collectors.toList());
    }
}
