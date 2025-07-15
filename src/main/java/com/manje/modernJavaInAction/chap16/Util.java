package com.manje.modernJavaInAction.chap16;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

//
public class Util {

    private static final Random RANDOM = new Random(0);
    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
    
    // 동기 API 처럼 구현하기 위한 delay() 함수
    public static void delay() {
        int delay = 1000;
        // int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // double 값을 #.## 꼴로 변경한 뒤, 다시 double 로 파싱하는 메서드 (즉, 반올림)
    public static double format(double number) {
        synchronized (formatter) {
            return Double.parseDouble(formatter.format(number));
        }
    }

    // List<CompletableFuture<T>> -> Stream<T> -> List<T> 로 바꾸는 연산을 비동기로 시작하고 반환함.
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
    /*
        CompletableFuture<Void> allDoneFuture =
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
            futures.stream()
                .map(future -> future.join())
                .collect(Collectors.<T>toList())
        );
    */
        return CompletableFuture.supplyAsync(() -> futures.stream()
                .map(future -> future.join())
                .collect(Collectors.<T>toList()));
    }

}

