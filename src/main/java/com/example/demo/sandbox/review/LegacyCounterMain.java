package com.example.demo.sandbox.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// дан легаси класс, необходимо его адаптировать для работы в многопоточной среде
@Slf4j
public class LegacyCounterMain {

    public static void main(String[] args) {
        var legacyCounter = Counter.of(0);
        var counterIncrementBorder = 10;
        var multithreadingCounterAdapterInstance = MultithreadingCounterAdapter.of(
                legacyCounter, counterIncrementBorder, new ReentrantLock(), new ReentrantLock()
        );

        Runnable runnableCounter = () -> {
                multithreadingCounterAdapterInstance.incrementConcurrentCounter();
                log.info("Текущий счетчик: counter = {}, thread = {}", multithreadingCounterAdapterInstance.getConcurrentCounter(), Thread.currentThread().getName());
        };

        Thread threadCounter1 = new Thread(runnableCounter);
        Thread threadCounter2 = new Thread(runnableCounter);
        Thread threadCounter3 = new Thread(runnableCounter);
        Thread threadCounter4 = new Thread(runnableCounter);

        threadCounter1.start();
        threadCounter2.start();
        threadCounter3.start();
        threadCounter4.start();

        try {
            threadCounter1.join();
            threadCounter2.join();
            threadCounter3.join();
            threadCounter4.join();

            var finalCounter = multithreadingCounterAdapterInstance.getConcurrentCounter();
            log.info("Результат вычисления счетчика: finalCounter = {}, thread = {}", finalCounter, Thread.currentThread().getName());

        } catch (InterruptedException interruptedException) {
            log.error("Ошибка мультипоточной среды: {}", interruptedException.getMessage());
        }
    }

    @AllArgsConstructor(staticName = "of")
    @Data
    static class Counter {
        private int count;
    }

    @AllArgsConstructor(staticName = "of")
    @Slf4j
    static class MultithreadingCounterAdapter {

        private Counter counter;

        private int counterIncrementBorder;

        private Lock incrementLock;

        private Lock getCounterValueLock;

        public void incrementConcurrentCounter() {
            incrementLock.lock();
            try {
                var currentCounterValue = getConcurrentCounter(); // обеспечить получение counter
                for (int i = 0; i < counterIncrementBorder; i++) {
                    var increment = ++currentCounterValue;
                    log.warn("\t Инкремент: currentValue = {}, thread = {}", increment, Thread.currentThread().getName());
                    counter.setCount(increment);
                }
            } finally {
                incrementLock.unlock();
            }
        }

        public int getConcurrentCounter() {
            getCounterValueLock.lock();
            var result = -1;
            try {
                result = counter.getCount();
            } finally {
                getCounterValueLock.unlock();
            }
            return result;
        }

    }

}
