package com.manje.modernJavaInAction.chap13.mostSpecific;

// A의 디폴트 메서드를 재정의함
public interface B extends A {

    @Override
    public default void hello() {
        System.out.println("Hello from B");
    }

}
