package com.manje.modernJavaInAction.chap15;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Functions {

    public static void main(String[] args) {
        int x = 5;
        sequential(x);
        try {
            futureBased(x);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    // 동기 API 방식으로 구현한 f, g 함수
    public static int f(int x) {
        return x * 2;
    }

    public static int g(int x) {
        return x + 1;
    }

    public static Integer fo(int x) {
        return Integer.valueOf(x * 2);
    }

    public static Integer go(int x) {
        return Integer.valueOf(x + 1);
    }
    
    // 비동기 API 방식으로 구현한 f, g 함수
    public static Future<Integer> ff(int x) {
        return new CompletableFuture<Integer>().completeAsync(() -> Integer.valueOf(x * 2));
    }

    public static Future<Integer> gf(int x) {
        return new CompletableFuture<Integer>().completeAsync(() -> Integer.valueOf(x + 1));
    }
    
    // 동기 API로 순차적으로 처리하는 함수
    private static void sequential(int x) {
        int y = f(x);
        int z = g(x);
        System.out.println(y + z);
    }
    
    // 비동기 API로 병렬로 처리하는 함수
    private static void futureBased(int x) throws InterruptedException, ExecutionException {
        Future<Integer> y = ff(x);
        Future<Integer> z = gf(x);
        System.out.println(y.get() + z.get()); // 여기서 y, z가 종료되기를 기다림 (blocking)
    }

}

