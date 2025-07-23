package com.manje.modernJavaInAction.chap20;

import java.util.function.Function;
import java.util.stream.Stream;

public class Currying {

    public static void main(String[] args) {
        int r = multiply(2, 10);
        System.out.println(r);

        Stream.of(1, 3, 5, 7)
                .map(multiplyCurry(2))
                .forEach(System.out::println);
    }

    // 커링하지 않은 함수, 인수가 2개
    static int multiply(int x, int y) {
        return x * y;
    }

    // x를 인수로 받은 뒤, y를 받아 곱을 반환하는 함수를 반환한다. (커링)
    static Function<Integer, Integer> multiplyCurry(int x) {
        return (Integer y) -> x * y;
    }

}
