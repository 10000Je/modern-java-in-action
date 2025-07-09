package com.manje.modernJavaInAction.chap13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Intro {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        // sort는 List 인터페이스의 디폴트 메서드
        // naturalOrder는 Comparator 인터페이스의 정적 메서드
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
    }

}
