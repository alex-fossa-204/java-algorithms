package com.alexfossa204.algorithms.interview.tasks.multithreadings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLockTaskSimple {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class User {

        private String userName;

        public void sayHello(User user) {
            synchronized (this) {
                synchronized (user) {
                    log.info("{} said hello to {}", this.userName, user.getUserName());
                    user.sayHelloBack(this);
                }
            }
        }

        public void sayHelloBack(User user) {
            synchronized (this) {
                log.info("{} said hello back to {}", this.userName, user.getUserName());
            }
        }

    }

    @SneakyThrows
    public static void main(String[] args) {

        var user1 = new User("Alex");
        var user2 = new User("Ann");

        var th1 = new Thread(() -> user1.sayHello(user2));
        var th2 = new Thread(() -> user2.sayHello(user1));

        th1.start();
        th2.start();

    }


}
