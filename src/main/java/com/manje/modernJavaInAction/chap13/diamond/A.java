package com.manje.modernJavaInAction.chap13.diamond;

public interface A {

    public default void hello() {
        System.out.println("Hello from A");
    }

}
