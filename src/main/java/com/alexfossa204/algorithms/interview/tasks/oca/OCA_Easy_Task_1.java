package com.alexfossa204.algorithms.interview.tasks.oca;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;

@Slf4j
public class OCA_Easy_Task_1 {

    public static void main(String[] args) {

        //1 скомпилируется ли данный код?
        Person[] personArray = {
                new Person("any_user_1", 25, List.of(Product.of("phone"), Product.of("laptop"))),
                new Person("any_user_2", 30, List.of(Product.of("laptop"), Product.of("pc"))),
                new Person("any_user_1", 25, List.of(Product.of("phone"), Product.of("ssd"))),
        };


        //2 какой результат выполнения данного кода
        var userSet = new HashSet<Person>();
        userSet.add(
                new Person("any_user_1", 25, List.of(Product.of("phone"), Product.of("laptop")))
        );
        userSet.add(
                new Person("any_user_2", 30, List.of(Product.of("laptop"), Product.of("pc")))
        );
        userSet.add(
                new Person("any_user_1", 25, List.of(Product.of("phone"), Product.of("laptop")))
        );
        userSet.forEach(item -> log.info("item: {}", item));


    }

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    static class Person {

        private String username;

        private int age;

        private List<Product> products;

    }

    @AllArgsConstructor(staticName = "of")
    @Data
    static class Product {

        private String productName;

    }

}
