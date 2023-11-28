package com.alexfossa204.algorithms.interview.tasks.oca;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OCA_Initialization_Block_Task1 {

    public static void main(String[] args) {

        //1
        exec_task1();

        //2
        exec_task2();

    }

    static void exec_task1() {
        var book = new Book("Thinking in Java", 100);
        log.info("book = {}", book);
    }

    static void exec_task2() {
        loop1:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 3)
                    break loop1;
                log.info("i = {}, j = {}", i, j);
            }
        }
    }

    @Slf4j
    @ToString
    static class Book {

        private String title;

        private double price;

        private boolean isPopular;

        {
            price = 200;
            log.info("[init-block]-price");
        }

        public Book() {
            this("default_book", 0.0d, false);
            log.info("[constructor-(empty)]");
        }

        public Book(String title) {
            this.title = title;
            log.info("[constructor-(title)]");
        }

        public Book(String title, double price) {
            this(title);
            this.price = price;
            log.info("[constructor-(title, price)]");
        }

        public Book(String title, double price, boolean isPopular) {
            this(title, price);
            this.isPopular = isPopular;
            log.info("[constructor-(title, price, isPopular)]");
        }
        double a = 5.4d;
        int c = (int) a;
    }

}


//1
//[init-block]-price
//[constructor-(title)]
//[constructor-(title, price)]
//OCA_Initialization_Block_Task1.Book(title=Thinking in Java, price=100.0, isPopular=false)


//2
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 0, j = 0
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 0, j = 1
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 0, j = 2
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 0, j = 3
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 0, j = 4
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 1, j = 0
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 1, j = 1
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 1, j = 2
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 1, j = 3
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 1, j = 4
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 2, j = 0
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 2, j = 1
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 2, j = 2
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 2, j = 3
//20:27:47.905 [main] INFO com.alexfossa204.algorithms.interview.tasks.oca.OCA_Initialization_Block_Task1 -- i = 2, j = 4


