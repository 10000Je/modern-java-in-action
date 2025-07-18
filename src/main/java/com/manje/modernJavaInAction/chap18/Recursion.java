package com.manje.modernJavaInAction.chap18;

import java.util.stream.LongStream;

public class Recursion {

    public static void main(String[] args) {
        System.out.println(factorialIterative(5));
        System.out.println(factorialRecursive(5));
        System.out.println(factorialStreams(5));
        System.out.println(factorialTailRecursive(5));
    }
    
    // 반복문으로 구현, 하지만 외부 반복은 가변 객체를 실수로 변경할 가능성이 있음
    public static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }
    
    // 일반적인 재귀 호출로 구현, 스택 프레임은 불변 객체
    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }
    
    // 스트림의 reduce 메서드로 구현
    public static long factorialStreams(long n) {
        return LongStream.rangeClosed(1, n).reduce(1, (long a, long b) -> a * b);
    }

    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }
    
    // 꼬리 재귀로 구현한 재귀 함수, 스택 프레임이 무한정 쌓이지 않게 최적화할 여지가 있음.
    public static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }

}

