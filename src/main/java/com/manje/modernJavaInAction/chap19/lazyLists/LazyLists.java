package com.manje.modernJavaInAction.chap19.lazyLists;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyLists {

    public static void main(String[] args) {
        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));

        System.out.println(l.head());

        LazyList<Integer> numbers = from(2);
        int two = numbers.head(); // 2
        int three = numbers.tail().head(); // 3
        int four = numbers.tail().tail().head(); // 4
        System.out.println(two + " " + three + " " + four);

        numbers = from(2);
        int prime_two = primes(numbers).head(); // 2
        int prime_three = primes(numbers).tail().head(); // 3
        int prime_five = primes(numbers).tail().tail().head(); // 4(스킵), 5
        System.out.println(prime_two + " " + prime_three + " " + prime_five);

        // 자바는 꼬리 호출 제거 기능이 없으므로 스택오버플로가 발생할 때까지 실행됨
        // printAll(primes(from(2)));
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<Integer>(n, () -> from(n + 1));
    }

    // 에라토스테네스의 체 알고리즘을 게으르게 실행하는 함수.
    // head 를 이용해 수를 필터링하고, 필터링한 수들에 대해 재귀적으로 호출하는 구조이다.
    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    // 모든 요소를 출력하는 메서드, 단 자바는 꼬리재귀 최적화를 지원하지 않으므로 스택 오버플로우 발생
    static <T> void printAll(MyList<T> numbers) {
        if (numbers.isEmpty()) {
            return;
        }
        System.out.println(numbers.head());
        printAll(numbers.tail());
    }

}
