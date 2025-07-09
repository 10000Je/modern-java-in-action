package com.manje.modernJavaInAction.chap13.ambiguous;

public class Ambiguous {

    public static void main(String... args) {
        new C().hello(); // C에서 재정의된 hello 를 호출한다.
    }

}
