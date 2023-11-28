package com.alexfossa204.algorithms.interview.tasks.multithreadings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultithreadingCounterMainSimple {

    //Стартовое значение счетчика
    private static final int COUNTER_INITIAL_VALUE = 0;

    //Значение инкремента
    private static final int INCREMENTOR_DELTA = 1;

    //Количество итераций инкремента которое необходимо выполнить
    private static final int INCREMENTOR_LIMIT = 10;

    public static void main(String[] args) throws InterruptedException {

        var counter = Counter.of(COUNTER_INITIAL_VALUE);

        var incrementer1 = new Incrementer(counter, INCREMENTOR_DELTA, INCREMENTOR_LIMIT);
        incrementer1.start();

        var incrementer2 = new Incrementer(counter, INCREMENTOR_DELTA, INCREMENTOR_LIMIT);
        incrementer2.start();

        var incrementer3 = new Incrementer(counter, INCREMENTOR_DELTA, INCREMENTOR_LIMIT);
        incrementer3.start();

        log.info("Final Counter Result: {}, threadName: {}", counter.getValue(), Thread.currentThread().getName());

    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    @Data
    static class Counter {

        private int value;

    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    @Getter
    @Setter
    @Slf4j
    static class Incrementer extends Thread {

        private Counter counter;

        private int delta;

        private int limit;

        @Override
        public void run() {
            log.info("Initial counter value: {}, threadName = {}", counter.getValue(), Thread.currentThread().getName());
            for (int i = 0; i < limit; i++) {
                var counterTemp = counter.getValue();
                counter.setValue(counterTemp + delta);
                log.info("Current value: {}, threadName = {}", counter.getValue(), Thread.currentThread().getName());
            }
            log.info("Result counter value: {}, threadName = {}", counter.getValue(), Thread.currentThread().getName());
        }

    }

}
