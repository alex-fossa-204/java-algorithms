package com.alexfossa204.algorithms.sandbox.multithreadings;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class ThreadStatesTask {

//1. NEW: Поток был создан, но еще не запущен (метод start() еще не был вызван).
//
//2. RUNNABLE: Поток находится в состоянии выполнения или готов к выполнению (в очереди на исполнение).
//
//3. BLOCKED: Поток блокирован, ожидая монитор или блокировку для входа в критическую секцию.
//
//4. WAITING: Поток находится в состоянии ожидания, вызванном методами без таймаута, такими как wait(), join() или LockSupport.park().
//
//5. TIMED_WAITING: Поток находится в состоянии ожидания с таймаутом, вызванным методами с таймаутом, такими как wait(long timeout), join(long timeout) или Thread.sleep(long millis).
//
//6. TERMINATED: Поток завершил свое выполнение и больше не выполняется

    private static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread worker1 = new Thread(() -> {
            synchronized (monitor) {
                IntStream.range(0, 5)
                        .forEach(item -> {
                            log.info("Счетчик: {}", item);
                            wrappedSleep(2000);
                        });
            }
        });
        log.warn("Поток {} в состоянии: {}", worker1.getName(), worker1.getState()); //NEW

        worker1.start();
        log.warn("Поток {} в состоянии: {}", worker1.getName(), worker1.getState()); //RUNNABLE

        while (worker1.getState() != Thread.State.BLOCKED) {
            if (worker1.getState().equals(Thread.State.BLOCKED)) {
                log.warn("Поток {} в состоянии: {}", worker1.getName(), worker1.getState());
            }
        }

    }

    static void wrappedSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
    }

}
