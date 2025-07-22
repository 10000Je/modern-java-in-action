package com.manje.modernJavaInAction.chap19;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyMathUtils {

    public static void main(String[] args) {
        System.out.println(primes(25).map(String::valueOf).collect(Collectors.joining(", ")));
    }

    // 소수 판별 메서드로 필터링하여 무한 스트림에서 n개의 소수를 반환하는 메서드
    public static Stream<Integer> primes(int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(MyMathUtils::isPrime)
                .limit(n);
    }

    // 단순하게, 2부터 루트 n 까지의 수로 나누어서 소수 판별을 진행하는 메서드
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

}