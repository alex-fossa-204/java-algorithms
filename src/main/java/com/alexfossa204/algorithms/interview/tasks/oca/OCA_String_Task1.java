package com.alexfossa204.algorithms.interview.tasks.oca;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OCA_String_Task1 {

    public static void main(String[] args) {
        //1
        exec_task1();

        //2
        exec_task2();

        //3
        exec_task3();
    }

    static void exec_task1() {
        String str1 = "string1";
        String str2 = new String("string1");
        String str3 = "string1";

        if (str1 == str2) {
            log.info("str1 equal to str2");
        } else {
            log.info("str1 not equal to str2");
        }

        if (str1 == str3) {
            log.info("str1 equal to str3");
        } else {
            log.info("str1 not equal to str3");
        }
    }

    static void exec_task2() {
        String str1 = "Java";
        String str2 = str1 + "";
        String str3 = str1.concat("");

        log.info("str1 == str2 -> {}", str1 == str2);
        log.info("str1 == str3 -> {}", str1 == str3);
    }

    static void exec_task3() {
        StringBuilder sb1 = new StringBuilder("Java");
        StringBuilder sb2 = new StringBuilder("Java");
        if (sb1.equals(sb2)) {
            log.info("sb1 is equal to sb2");
        } else {
            log.info("sb1 is not equal to sb2");
        }
    }

}


//2
//20:03:26.136 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_String_Task1 -- str1 == str2 -> false
//20:03:26.137 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_String_Task1 -- str1 == str3 -> true