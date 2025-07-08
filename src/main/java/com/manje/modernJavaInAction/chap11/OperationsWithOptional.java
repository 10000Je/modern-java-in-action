package com.manje.modernJavaInAction.chap11;

import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class OperationsWithOptional {

    public static void main(String... args) {
        System.out.println(max(of(3), of(5))); // 5
        System.out.println(max(empty(), of(5))); // empty

        Optional<Integer> opt1 = of(5); // 5
        Optional<Integer> opt2 = opt1.or(() -> of(4)); // 4

        System.out.println(of(5).or(() -> of(4))); // 5
    }

    // i, j 중 최댓값을 Optional로 반환하는 메서드
    // 둘 중 하나가 빈 Optional 이면 최댓값이 정의가 안되므로 빈 Optional을 반환
    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
        return i.flatMap(a -> j.map(b -> Math.max(a, b))); // 개인적으로 이런 코드는 쓰레기라 생각함
    }

}
