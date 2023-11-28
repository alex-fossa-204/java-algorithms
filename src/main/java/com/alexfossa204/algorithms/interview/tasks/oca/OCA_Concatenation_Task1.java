package com.alexfossa204.algorithms.interview.tasks.oca;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OCA_Concatenation_Task1 {

    public static void main(String[] args) {

        //1
        exec_task1();

        //2
        exec_task2();

    }

    static void exec_task1() {
        String str = 56 + 4 + "Hello";
        log.info("result1 = {}", str);
    }

    static void exec_task2() {
        String str = "Hello" + 10 * 4;
        log.info("result1 = {}", str);
    }

}


//Ответ
// В java имеется приоритет операторов, в данной задаче + имеет одинаквый приотритет и компилятор определяет последовательность
// В 1 задании 56 и 4 - integer, Hello это строка, пожтому он сначала выполнит математику потом конкатенацию
// В 2 задании выполняется конкатенация, тк сложение строки и числа идет в первую очередь