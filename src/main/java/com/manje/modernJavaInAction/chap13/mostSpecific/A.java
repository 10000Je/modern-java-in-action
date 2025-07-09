package com.manje.modernJavaInAction.chap13.mostSpecific;

public interface A {

    public default void hello() {
        System.out.println("Hello from A");
    }

}
