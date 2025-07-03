package com.manje.modernJavaInAction.chap09.debugging;

import java.util.Arrays;
import java.util.List;

public class Debugging {
    
    // 일부러 null을 넣어 NPE 예외를 일으킨 예제
    // 스택트레이스에서 람다 표현식은 이름이 없어서 알아보기 힘듬
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(p -> p.getX()).forEach(System.out::println);
    }

}
