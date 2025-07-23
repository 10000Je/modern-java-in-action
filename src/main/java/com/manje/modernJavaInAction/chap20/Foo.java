package com.manje.modernJavaInAction.chap20;

import java.util.stream.IntStream;

public class Foo {

    // Stream API를 이용해 함수형으로 구현
    public static void main(String[] args) {
        IntStream.rangeClosed(2, 6)
                .forEach(n -> System.out.println("Hello " + n + " bottles of beer"));
    }

}