package com.manje.modernJavaInAction.chap15;

import static com.manje.modernJavaInAction.chap15.Functions.f;
import static com.manje.modernJavaInAction.chap15.Functions.g;

// Thread 를 이용한 비동기 처리 코드
public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        Result result = new Result();

        Thread t1 = new Thread(() -> {
            result.left = f(x);
        }); // f(x) 를 연산하는 스레드 생성
        Thread t2 = new Thread(() -> {
            result.right = g(x);
        }); // g(x) 를 연산하는 스레드 생성
        t1.start(); // 스레드 시작
        t2.start();
        t1.join(); // 스레드 종료 대기 (blocking)
        t2.join();
        System.out.println(result.left + result.right); // 4012
    }

}
