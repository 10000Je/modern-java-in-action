package com.manje.modernJavaInAction.chap19;

import java.util.function.Function;

public class Combinators {

    public static void main(String[] args) {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10)); // 80 출력
    }

    // A -> B, B -> C를 받아서, A -> C를 반환하는 함수
    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    // 함수 A -> A 를 n번 반복하는 함수를 반환한다.
    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
    }

}
