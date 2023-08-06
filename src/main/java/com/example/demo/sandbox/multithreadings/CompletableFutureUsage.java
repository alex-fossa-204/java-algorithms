package com.example.demo.sandbox.multithreadings;

import com.example.demo.stubFunctionality.entity.CreditRating;
import com.example.demo.stubFunctionality.service.CreditRatingService;
import com.example.demo.stubFunctionality.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class CompletableFutureUsage {

    private static UserService userService = new UserService();

    private static CreditRatingService creditRatingService = new CreditRatingService();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("Main started");
        completableFutureRunAsyncExample_return_Void();
        log.info("\n");

        var futureIncrementResult = completableFutureRunAsyncExample_return_Counter(5, 10);
        log.info("futureIncrementResult = {}", futureIncrementResult.get());
        log.info("\n");

        var populatedUserNameResult = completableFuture_callback_then_do_something_with_result("Rick Sunches");
        log.info("populatedUserNameResult = {}", populatedUserNameResult);
        log.info("\n");

        var userCreditRatingComposed = composeUserCreditService("rickSunches90");
        log.info("userCreditData = {}", userCreditRatingComposed);
        log.info("\n");

        var calculateUserBmiFuture = calculateUserBmiCombinedFuture(getWeightKgFuture(), getHeightMeterFuture());
        log.info("userBmiData = {}", calculateUserBmiFuture.get());
        log.info("\n");

        var userCorrectData1 = checkAgeWithExceptionallyHandler(15);
        log.info("userCorrectData1 = {}", userCorrectData1.get());
        var userCorrectData2 = checkAgeWithExceptionallyHandler(21);
        log.info("userCorrectData2 = {}", userCorrectData2.get());
        var userIncorrectData1 = checkAgeWithExceptionallyHandler(-5);
        log.info("userIncorrectData = {}", userIncorrectData1.get());
        log.info("\n");

        var userCorrectData3 = checkAgeWithHandle(15);
        log.info("userCorrectData1 = {}", userCorrectData3.get());
        var userCorrectData4 = checkAgeWithHandle(21);
        log.info("userCorrectData2 = {}", userCorrectData4.get());
        var userIncorrectData2 = checkAgeWithHandle(-5);
        log.info("userIncorrectData = {}", userIncorrectData2.get());
        log.info("\n");

        log.info("Main completed\n");
    }

    //Запуск параллельного потока без получения результата — метод runAsync()
    static void completableFutureRunAsyncExample_return_Void() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidFuture = CompletableFuture.runAsync(() -> {
            try {
                log.info("Наичанем спать..");
                TimeUnit.MILLISECONDS.sleep(400);
                log.info("Мы проснулись :)");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Что-то делаем в отдельном потоке:");
            var myIncrement = new AtomicInteger();
            IntStream.range(0, 10)
                    .peek(item -> surroundedSleep(TimeUnit.MILLISECONDS, 400))
                    .forEach(item -> log.info("Инкремент: {}", myIncrement.getAndIncrement()));
        });
        voidFuture.get();
    }

    //Запуск параллельного потока с получением результата — метод  supplyAsync()
    static AtomicInteger completableFutureRunAsyncExample_return_Counter(Integer startCounter, Integer maxBorder) throws InterruptedException, ExecutionException {
        var completableFuture = CompletableFuture.supplyAsync(() -> {
            var atomicInteger = new AtomicInteger(startCounter);
            IntStream.range(0, maxBorder)
                    .peek(item -> {
                        surroundedSleep(TimeUnit.MILLISECONDS, 400);
                    })
                    .forEach(item -> {
                        atomicInteger.incrementAndGet();
                        log.info("текущий счетчик: {}, result = {}", item, atomicInteger.get());
                    });
            return atomicInteger;
        });
        return completableFuture.get();
    }

    //Callback-функции в CompletableFuture thenApply() и thenAsync()
    //Допустим, нам надо закончить вычисления в supplyAsync() и далее сделать что-то с результатом, не блокируя текущие поток.
    static String completableFuture_callback_then_do_something_with_result(String usernameStub) throws ExecutionException, InterruptedException {
        var whatIsYourNameCompletableFuture = CompletableFuture.supplyAsync(
                () -> {
                    log.info("Выполняем запрос на сервер: get username");
                    surroundedSleep(TimeUnit.MILLISECONDS, 2);
                    return usernameStub;
                }
        ).thenApply(name -> { //Выполняется в том же потоке, где и задача supplyAsync()
            log.info("Получаем ответ от сервера: имя пользователя = {}", name);
            return String.format("username = %s, sessionId = %s", name, UUID.randomUUID());
        }).thenApplyAsync(name -> { //Выполняется в другом потоке, взятом из ForkJoinPool.commonPool()
            log.info("Получаем ответ от сервера: имя пользователя = {}", name);
            return String.format("{ workerNode: ITEM-1, user: { %s } }", name);
        });
        return whatIsYourNameCompletableFuture.get();
    }

    //Комбинация двух CompletableFutures
    //thenCompose() для двух зависимых друг от друга CompletableFutures
    static CreditRating composeUserCreditService(String username) throws ExecutionException, InterruptedException {
        return userService.findUserByUsernameFuture(username)
                .thenCompose(user -> creditRatingService.findUserCreditRatingFuture(user)).get();
    }

    //thenCombine() для двух независимых друг от друга CompletableFutures
    //Первый CompletableFuture возвращает вес, второй — рост. Когда оба значения вычислятся, мы рассчитываем индекс bmi. Он вычисляется в callback-функции, переданной в метод thenCombine():
    static CompletableFuture<Double> getWeightKgFuture() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Выполняем запрос на сервер: получаем данные веса пользователя");
            surroundedSleep(TimeUnit.MILLISECONDS, 2);
            return 85.25d;
        });
    }

    static CompletableFuture<Double> getHeightMeterFuture() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Выполняем запрос на сервер: получаем данные роста пользователя");
            surroundedSleep(TimeUnit.MILLISECONDS, 2);
            return 1.85d;
        });
    }

    static CompletableFuture<Double> calculateUserBmiCombinedFuture(CompletableFuture<Double> userWeightDataFuture, CompletableFuture<Double> userHeightDataFuture) {
        return userWeightDataFuture.thenCombine(
                userHeightDataFuture,
                (weight, height) -> weight / (height * weight)
        );
    }

    //Обработка исключений при работе с CompletableFuture
    //.exceptionally()
    static CompletableFuture<String> checkAgeWithExceptionallyHandler(Integer age) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }
            if (age > 18) {
                return "СОВЕРШЕННОЛЕТНИЙ";
            }
            if (age < 18) {
                return "НЕСОВЕРШЕННОЛЕТНИЙ";
            }
            return "UNDEFINED";
        }).exceptionally(exception -> {
            log.error("Обработано исключение: message = {}", exception.getMessage());
            return "ОШИБКА";
        }).thenApply(ageIndex -> {
            log.info("Зрелость пользователя: ageIndex = {}", ageIndex);
            return ageIndex;
        });
    }

    //.handle()
    static CompletableFuture<String> checkAgeWithHandle(Integer age) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }
            if (age > 18) {
                return "СОВЕРШЕННОЛЕТНИЙ";
            }
            if (age < 18) {
                return "НЕСОВЕРШЕННОЛЕТНИЙ";
            }
            return "UNDEFINED";
        }).handle((result, exception) -> {
            if (exception != null) {
                log.error("Обработано исключение: message = {}", exception.getMessage());
                return "ОШИБКА";
            }
            return "UNDEFINED";
        });
    }

    static void surroundedSleep(TimeUnit timeUnit, long timeToSleep) {
        try {
            timeUnit.sleep(timeToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
