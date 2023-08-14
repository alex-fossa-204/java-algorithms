package com.alexfossa204.algorithms.sandbox.multithreadings;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SynchronizedIncrement {

    public static void main(String[] args) throws InterruptedException {

        var initialValue = 0;
        var incrementProcessor = new IncrementProcessor(initialValue);

        var executorService = Executors.newFixedThreadPool(3);

        var tasks = List.of(
                IncrementTaskFactory.supplyIncrementTask(incrementProcessor, 1000),
                IncrementTaskFactory.supplyIncrementTask(incrementProcessor, 500),
                IncrementTaskFactory.supplyIncrementTask(incrementProcessor, 10)
        );
        executorService.invokeAll(tasks);
        executorService.shutdown();

        log.info("Результат инкремента: result = {}", incrementProcessor.getCounter());

    }

    static class IncrementProcessor {

        private AtomicInteger counter;

        IncrementProcessor(Integer initialValue) {
            counter = new AtomicInteger(initialValue);
        }

        Integer incrementCounter() {
            return counter.incrementAndGet();
        }

        AtomicInteger getCounter() {
            return counter;
        }

    }

    @Data
    static class IncrementTaskFactory {
        static Callable<Integer> supplyIncrementTask(IncrementProcessor incrementProcessor, Integer incrementTaskBorder) {
            return () -> {
                var currentCounter = incrementProcessor.getCounter().get();
                for (int i = 0; i < incrementTaskBorder; i++) {
                    currentCounter = incrementProcessor.incrementCounter();
                    log.info("Текущий инкремент: counter = {}, threadName = {}", currentCounter, Thread.currentThread().getName());
                }
                return currentCounter;
            };
        }
    }

}
