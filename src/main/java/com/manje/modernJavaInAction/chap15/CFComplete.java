package com.manje.modernJavaInAction.chap15;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.manje.modernJavaInAction.chap15.Functions.f;
import static com.manje.modernJavaInAction.chap15.Functions.g;

// 태스크의 수가 적다면 문제가 없지만, 많아지면 get 의 호출이 점점 복잡해짐.
public class CFComplete {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        CompletableFuture<Integer> a = new CompletableFuture<>();
        executorService.submit(() -> a.complete(f(x))); // 비동기 실행
        int b = g(x);
        System.out.println(a.get() + b); // get (blocking)

        executorService.shutdown();
    }

}

