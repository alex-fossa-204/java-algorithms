package com.example.demo.sandbox.string;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class StringConstructorTask {

    /*
     * You probably know the "like" system from Facebook and other pages. People can "like" blog posts, pictures or other items. We want to create the text that should be displayed next to such an item.
     * Implement the function which takes an array containing the names of people that like an item. It must return the display text as shown in the examples:
     *
     * example
     * []                                -->  "no one likes this"
     * ["Peter"]                         -->  "Peter likes this"
     * ["Jacob", "Alex"]                 -->  "Jacob and Alex like this"
     * ["Max", "John", "Mark"]           -->  "Max, John and Mark like this"
     * ["Alex", "Jacob", "Mark", "Max"]  -->  "Alex, Jacob and 2 others like this"
     */
    public static void main(String[] args) {
        var strings = List.of(
                new String[]{},
                new String[]{"Peter"},
                new String[]{"Jacob", "Alex"},
                new String[]{"Max", "John", "Mark"},
                new String[]{"Alex", "Jacob", "Mark", "Max"}

        );
        strings.forEach(item -> log.info("Результат построения строки: result = {}", executeStringConstruction(item)));

    }

    public static String executeStringConstruction(String[] strings) {
        StringConstructorStrategyResolver stringConstructorStrategyResolver = StringConstructorStrategyResolverImpl.of(
                List.of(
                        EmptyLikerStringConstructorStrategy.create(),
                        SingleLikerStringConstructionStrategy.create(),
                        TwoLikesStringConstructionStrategy.create(),
                        ThreeLikesStringConstructionStrategy.create(),
                        FourOthersLikesStringConstructionStrategy.create()
                )
        );
        var stringConstructorStrategy = stringConstructorStrategyResolver.resolveStringConstructorStrategy(strings);
        return stringConstructorStrategy.isPresent() ? stringConstructorStrategy.get().executeStringConstructionStrategy(strings) : String.format("Входные параметры не позволяют построить строку корректно: params = %s", Arrays.toString(strings));
    }

    /**
     * Компонент предназначенный для определения стратегии формирования строки
     */
    interface StringConstructorStrategyResolver {

        /**
         * Определить стратегию для формирования строки
         * @param strings исходные данные
         * @return стратегия формирования строки
         */
        Optional<StringConstructorStrategy> resolveStringConstructorStrategy(String[] strings);

    }

    @RequiredArgsConstructor(staticName = "of")
    static class StringConstructorStrategyResolverImpl implements StringConstructorStrategyResolver {

        private final List<StringConstructorStrategy> stringConstructorStrategies;

        @Override
        public Optional<StringConstructorStrategy> resolveStringConstructorStrategy(String[] strings) {
            return stringConstructorStrategies.stream()
                    .filter(strategy -> strategy.isStrategyResolved(strings))
                    .findFirst();
        }

    }

    /**
     * Компонент, предназначенный для определения стратегии формирования строки из массива строк
     */
    interface StringConstructorStrategy {

        /**
         * Определить - подходит ли стратегия формирования строки при текущих условиях
         * @param strings исходные данные
         * @return готовая строка
         */
        Boolean isStrategyResolved(String[] strings);

        /**
         * Выполнить стратегию формирования строки
         * @param strings исходные данные
         * @return готовая строка
         */
        String executeStringConstructionStrategy(String[] strings);

    }

    @AllArgsConstructor(staticName = "create")
    static class EmptyLikerStringConstructorStrategy implements StringConstructorStrategy {

        private static final String STRING_CONSTRUCTION_PATTERN = "no one likes this";

        @Override
        public Boolean isStrategyResolved(String[] strings) {
            return strings.length == 0;
        }

        @Override
        public String executeStringConstructionStrategy(String[] strings) {
            return STRING_CONSTRUCTION_PATTERN;
        }
    }

    @AllArgsConstructor(staticName = "create")
    static class SingleLikerStringConstructionStrategy implements StringConstructorStrategy {

        private static final String STRING_CONSTRUCTION_PATTERN = "%s likes this";

        @Override
        public Boolean isStrategyResolved(String[] strings) {
            return strings.length == 1;
        }

        @Override
        public String executeStringConstructionStrategy(String[] strings) {
            return String.format(STRING_CONSTRUCTION_PATTERN, strings[0]);
        }
    }

    @AllArgsConstructor(staticName = "create")
    static class TwoLikesStringConstructionStrategy implements StringConstructorStrategy {

        private static final String STRING_CONSTRUCTION_PATTERN = "%s and %s like this";

        @Override
        public Boolean isStrategyResolved(String[] strings) {
            return strings.length == 2;
        }

        @Override
        public String executeStringConstructionStrategy(String[] strings) {
            return String.format(STRING_CONSTRUCTION_PATTERN, strings[0], strings[1]);
        }
    }

    @AllArgsConstructor(staticName = "create")
    static class ThreeLikesStringConstructionStrategy implements StringConstructorStrategy {

        private static final String STRING_CONSTRUCTION_PATTERN = "%s, %s and %s like this";

        @Override
        public Boolean isStrategyResolved(String[] strings) {
            return strings.length == 3;
        }

        @Override
        public String executeStringConstructionStrategy(String[] strings) {
            return String.format(STRING_CONSTRUCTION_PATTERN, strings[0], strings[1], strings[2]);
        }
    }

    @AllArgsConstructor(staticName = "create")
    static class FourOthersLikesStringConstructionStrategy implements StringConstructorStrategy {

        private static final String STRING_CONSTRUCTION_PATTERN = "%s, %s and %d others like this";

        @Override
        public Boolean isStrategyResolved(String[] strings) {
            return strings.length >= 4;
        }

        @Override
        public String executeStringConstructionStrategy(String[] strings) {
            return String.format(STRING_CONSTRUCTION_PATTERN, strings[0], strings[1], strings.length - 2);
        }
    }

}
