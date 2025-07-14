package com.manje.modernJavaInAction.chap15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.manje.modernJavaInAction.chap15.Functions.fo;
import static com.manje.modernJavaInAction.chap15.Functions.go;

public class ExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1337;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> y = executorService.submit(() -> fo(x)); // 람다식을 비동기로 실행. 반환되는 Future로 결과에 접근가능
        Future<Integer> z = executorService.submit(() -> go(x));
        System.out.println(y.get() + z.get()); // get 으로 결과를 대기 (blocking)

        executorService.shutdown();
    }

}
