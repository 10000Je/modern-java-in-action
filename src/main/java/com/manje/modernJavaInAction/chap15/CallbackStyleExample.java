package com.manje.modernJavaInAction.chap15;

import java.util.function.IntConsumer;

// 리액티브 형식 API, 콜백 함수를 넘겨서 처리함
public class CallbackStyleExample {

    public static void main(String[] args) {

        int x = 1337;
        Result result = new Result();

        f(x, (int y) -> {
            result.left = y;
            System.out.println((result.left + result.right)); // 1337 * 2 = 2674
        });

        g(x, (int z) -> {
            result.right = z;
            System.out.println((result.left + result.right)); // 2674 + 1338 = 4012
        });
    }

    static void f(int x, IntConsumer dealWithResult) {
        dealWithResult.accept(Functions.f(x)); // x * 2
    }

    static void g(int x, IntConsumer dealWithResult) {
        dealWithResult.accept(Functions.g(x)); // x + 1
    }

}
