package com.alexfossa204.algorithms.interview.tasks.oca;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OCA_Static_Initialization_Task1 {

    public static void main(String[] args) {
        //Какой будет результат выполнения кода
        log.info("static final variable is: {}", FinalVar.staticFinalVar);
    }

    @Slf4j
    static class FinalVar {
        public static final int staticFinalVar = 5;
        public static final int staticVar;

        static {
            staticVar = 10;
            log.info("[Static-block] staticFinalVar = {}", staticFinalVar);
            log.info("[Static-block] staticVar = {}", staticVar);
        }
    }

}
