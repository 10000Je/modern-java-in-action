package com.manje.modernJavaInAction.chap10;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

// 내부 DSL 중 코드의 신호 대비 잡음 비율을 설명하는 부분
public class PrintNumbers {

    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("one", "two", "three");
        
        // 익명 클래스를 이용한 구현, 기능에 비해 부가적인 코드가 장황함
        System.out.println("Anonymous class:");
        numbers.forEach(new Consumer<String>() {

            @Override
            public void accept(String s) {
                System.out.println(s);
            }

        });
        
        // 람다 표현식과 메서드 참조를 통해 잡음을 줄일 수 있음
        System.out.println("Lambda expression");
        numbers.forEach(s -> System.out.println(s));

        System.out.println("method reference:");
        numbers.forEach(System.out::println);
    }

}
