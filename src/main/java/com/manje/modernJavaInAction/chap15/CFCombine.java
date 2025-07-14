package com.manje.modernJavaInAction.chap15;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.manje.modernJavaInAction.chap15.Functions.f;
import static com.manje.modernJavaInAction.chap15.Functions.g;

// themCombine 메서드로 생성한 c는 바로 스레드 풀에 제출되지 않음
// a, b가 연산을 마치면 이후 스레드 풀에 제출되어 연산을 실행 (non-blocking)
// 최종적으로 메인 스레드의 한번의 get 으로 결과를 받아와 복잡성이 낮고 안전함
public class CFCombine {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        CompletableFuture<Integer> a = new CompletableFuture<>();
        CompletableFuture<Integer> b = new CompletableFuture<>();
        CompletableFuture<Integer> c = a.thenCombine(b, (y, z)-> y + z); // non-blocking
        executorService.submit(() -> a.complete(f(x)));
        executorService.submit(() -> b.complete(g(x)));

        System.out.println(c.get()); // blocking
        executorService.shutdown();
    }

}
