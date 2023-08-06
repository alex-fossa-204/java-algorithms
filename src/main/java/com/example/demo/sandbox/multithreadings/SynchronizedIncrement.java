package com.example.demo.sandbox.multithreadings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

@Slf4j
public class SynchronizedIncrement {

    private static final Object mainMonitor = new Object();

    public static void main(String[] args) {

//        synchronized (mainMonitor) {
//            var incrementer1 = Incrementer.of(0);
//            var range1 = 50;
//            var monitor1 = new Object();
//
//            new Thread(IncrementRunnableSynchronization.of(incrementer1, range1, monitor1)).start();
//            new Thread(IncrementRunnableSynchronization.of(incrementer1, range1, monitor1)).start();
//            new Thread(IncrementRunnableSynchronization.of(incrementer1, range1, monitor1)).start();
//        }
//
//        synchronized (mainMonitor) {
//            var incrementer2 = Incrementer.of(0);
//            var range2 = 100;
//            var reentrantLock2 = new ReentrantLock();
//            var workerType2 = "ThreadStart";
//
//            new Thread(IncrementRunnableLockSynchronization.of(reentrantLock2, incrementer2, range2, workerType2)).start();
//            new Thread(IncrementRunnableLockSynchronization.of(reentrantLock2, incrementer2, range2, workerType2)).start();
//            new Thread(IncrementRunnableLockSynchronization.of(reentrantLock2, incrementer2, range2, workerType2)).start();
//        }

        synchronized (mainMonitor) {
            var atomicInteger = new AtomicInteger(0);
            var range2 = 100;
            var workerType2 = "AtomicIncrement";
            var lock = new ReentrantLock();

            new Thread(IncrementRunnableAtomic.of(atomicInteger, range2, workerType2, lock)).start();
            new Thread(IncrementRunnableAtomic.of(atomicInteger, range2, workerType2, lock)).start();
            new Thread(IncrementRunnableAtomic.of(atomicInteger, range2, workerType2, lock)).start();
        }

    }


    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    @Data
    static class Incrementer {

        private int increment;

        public int increment() {
            return ++increment;
        }

    }

    @AllArgsConstructor(staticName = "of")
    @Data
    @Slf4j
    static class IncrementRunnableSynchronization implements Runnable {

        private Incrementer incrementer;

        private volatile Integer range;

        private final Object monitor;

        @Override
        public void run() {
            IntStream.range(0, range)
                    .forEach(item -> {
                        synchronized (monitor) {
                            incrementer.increment();
                            log.info("Текущий поток: {} -> Текущий инкремент: {}", Thread.currentThread().getName(), incrementer.getIncrement());
                        }
                    });
        }
    }

    @AllArgsConstructor(staticName = "of")
    @Data
    @Slf4j
    static class IncrementRunnableLockSynchronization implements Runnable {

        private final Lock lock;

        private Incrementer incrementer;

        private volatile Integer range;

        private final String workerType;

        @Override
        public void run() {
            IntStream.range(0, range)
                    .forEach(item -> {
                        lock.lock();
                        try {
                            log.info("Текущий поток:  {} -> поставил блокировку | workerType = {}", Thread.currentThread().getName(), workerType);
                            incrementer.increment();
                            log.info("Текущий поток: {} -> Текущий инкремент: {}", Thread.currentThread().getName(), incrementer.getIncrement());
                        } finally {
                            log.info("Текущий поток: {} -> снял блокировку | workerType = {}\n", Thread.currentThread().getName(), workerType);
                            lock.unlock();
                        }
                    });
        }
    }

    @AllArgsConstructor(staticName = "of")
    @Data
    @Slf4j
    static class IncrementRunnableAtomic implements Runnable {

        private AtomicInteger atomicInteger;

        private volatile Integer range;

        private final String workerType;

        private Lock lock;

        @Override
        public void run() {
            IntStream.range(0, range)
                    .forEach(item -> {
                                lock.lock();
                                log.info("Текущий поток: {} -> Текущий инкремент: {} | workerType = {}", Thread.currentThread().getName(), atomicInteger.incrementAndGet(), workerType);
                                lock.unlock();
                            }
                    );
        }
    }

}
