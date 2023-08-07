package com.example.demo.sandbox.multithreadings;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleDeadLockExample {

    @Slf4j
    static class SimpleDeadlockExample {
        public static void main(String[] args) {
            Object resource1 = new Object();
            Object resource2 = new Object();

            // Поток 1
            Thread thread1 = new Thread(() -> {
                synchronized (resource1) {
                    log.info("Thread 1: Holding resource1...");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    log.info("Thread 1: Waiting for resource2...");
                    synchronized (resource2) {
                        log.info("Thread 1: Holding resource1 and resource2.");
                    }
                }
            });

            // Поток 2
            Thread thread2 = new Thread(() -> {
                synchronized (resource2) {
                    log.info("Thread 2: Holding resource2...");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    log.info("Thread 2: Waiting for resource1...");
                    synchronized (resource1) {
                        log.info("Thread 2: Holding resource2 and resource1.");
                    }
                }
            });

            // Запускаем оба потока
            thread1.start();
            thread2.start();
        }
    }

    @Slf4j
    static class DeadlockFixExample {
        public static void main(String[] args) {
            Lock lock1 = new ReentrantLock();
            Lock lock2 = new ReentrantLock();

            // Поток 1
            Thread thread1 = new Thread(() -> {
                boolean lock1Acquired = lock1.tryLock();
                if (lock1Acquired) {
                    try {
                        log.info("Thread 1: Holding lock1...");

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        log.info("Thread 1: Waiting for lock2...");
                        boolean lock2Acquired = lock2.tryLock();
                        if (lock2Acquired) {
                            try {
                                log.info("Thread 1: Holding lock1 and lock2.");
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                            log.info("Thread 1: Cannot acquire lock2, releasing lock1...");
                        }
                    } finally {
                        lock1.unlock();
                    }
                } else {
                    log.info("Thread 1: Cannot acquire lock1.");
                }
            });

            // Поток 2
            Thread thread2 = new Thread(() -> {
                boolean lock2Acquired = lock2.tryLock();
                if (lock2Acquired) {
                    try {
                        log.info("Thread 2: Holding lock2...");

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        log.info("Thread 2: Waiting for lock1...");
                        boolean lock1Acquired = lock1.tryLock();
                        if (lock1Acquired) {
                            try {
                                log.info("Thread 2: Holding lock2 and lock1.");
                            } finally {
                                lock1.unlock();
                            }
                        } else {
                            log.info("Thread 2: Cannot acquire lock1, releasing lock2...");
                        }
                    } finally {
                        lock2.unlock();
                    }
                } else {
                    log.info("Thread 2: Cannot acquire lock2.");
                }
            });

            // Запускаем оба потока
            thread1.start();
            thread2.start();
        }
    }

}
