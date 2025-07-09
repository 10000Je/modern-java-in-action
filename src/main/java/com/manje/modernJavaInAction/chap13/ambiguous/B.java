package com.manje.modernJavaInAction.chap13.ambiguous;

public interface B {

    public default void hello() {
        System.out.println("Hello from B");
    }

}
