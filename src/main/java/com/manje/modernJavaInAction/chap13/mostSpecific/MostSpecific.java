package com.manje.modernJavaInAction.chap13.mostSpecific;

public class MostSpecific {

    public static void main(String... args) {
        new C().hello(); // B의 디폴트 메서드가 호출됨
        new E().hello(); // B의 디폴트 메서드가 호출됨
        new G().hello(); // F의 메서드가 호출됨
    }

}
