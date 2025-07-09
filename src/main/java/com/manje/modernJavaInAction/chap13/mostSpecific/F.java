package com.manje.modernJavaInAction.chap13.mostSpecific;

// 재정의된 F의 메서드가 호출됨
public class F implements A, B {

    @Override
    public void hello() {
        System.out.println("Hello from F");
    }

}
