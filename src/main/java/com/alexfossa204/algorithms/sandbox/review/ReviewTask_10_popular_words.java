package com.alexfossa204.algorithms.sandbox.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class ReviewTask_10_popular_words { // 1) необходимо вынести базовые методы сервиса в интерфейс и написать документацию к классу и методу

    private static ArrayList<String> words; //3) следует сделать локальной переменной, тк хранение извлекаемых данных недопустимо, тк данные могут быть модифицированы другим потоком tomcat/jetty
    private static final int wordsCount = 10; //4) следует переименовать константу с более точным именем и в uppercase

    public static ArrayList<String> getWords() { // 2) статические методы не стоит использовать в spring компонентах тк эти методы принадлежат к классу и мы лишаемся преимуществ AOP
        words = new ArrayList<>(); // 5) следует сделать локальной переменной
        String response = requestRandomWord(); // 6) следует вынести логику обращения в другой сервис / ресурс
        log.debug("getWords: " + response); // 7) лог неинформативный следует привести логирование к best practice
        for (int i = 0; i < wordsCount; i++) {
            try {
                words.add(
                        new JSONArray(response).getJSONObject(i) // 12) сам процесс мне кажется в корне некорректый мы по индексу обращаемся в массив json, обходим по внешнему дапазану и дичь какая-то в общем
                                .getString("word")
                );
            } catch (Exception e) {
                e.printStackTrace(); // 8) следует продумать обработку исключения и его логирование, убрать stack trace, по хорошему вынести за цикл
            }
            return words;
        }


        return words; // 9) отсутствует корневой return
    }

    private static String requestRandomWord() { //10) вынести логику в отдельный компонент и инжектить его в сервис
        return "10.5.12.5"; // 11) вынести константу ip адреса внешней системы в проверти файл и инежтить его через @Value или вынести в класс @ConfigurationProperty
    }

}
