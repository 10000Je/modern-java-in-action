package com.manje.modernJavaInAction.chap02;

import java.util.List;

public class Quiz {

    // 2-1
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter f) {
        for(Apple apple : inventory) {
            String output = f.format(apple);
            System.out.println(output);
        }
    }

}
