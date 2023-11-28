package com.alexfossa204.algorithms.interview.tasks.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.ArrayList;



/**
 * Компонент предназначенный для получения популярных слов из некоторого источника
 */
//Задача: Необходимо смоделировать твои действия на Pull (Merge) Request - оставить комментарии и вопросы по коду
@Slf4j
@Service
public class WordsExtractorComponent {

    private static ArrayList<String> words;

    private static final int wordsCount = 10;

    public static ArrayList<String> getWords() {
        words = new ArrayList<>();
        String response = requestRandomWord();
        log.debug("getWords: " + response);
        for (int i = 0; i < wordsCount; i++) {
            try {
                words.add(
                        new JSONArray(response).getJSONObject(i)
                                .getString("word")
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return words;
        }

        return words;
    }

    private static String requestRandomWord() {
        return "10.5.12.5";
    }

}
