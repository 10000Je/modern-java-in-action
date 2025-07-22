package com.manje.modernJavaInAction.chap19.lazyLists;

import java.util.function.Predicate;

// 간단한 연결리스트 인터페이스, head는 현재 값을, tail은 나머지 리스트를 나타냄
public interface MyList<T> {

    T head();

    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }

    MyList<T> filter(Predicate<T> p);

}