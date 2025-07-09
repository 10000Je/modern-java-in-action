package com.manje.modernJavaInAction.chap13.ambiguous;

public class C implements A, B {

    @Override
    public void hello() {
        A.super.hello();
    }

}
