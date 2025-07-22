package com.manje.modernJavaInAction.chap19.lazyLists;

import java.util.function.Predicate;

// 빈 리스트를 나타내는 클래스, 모든 메서드는 UnsupportedOperationException을 던짐
public class Empty<T> implements MyList<T> {

    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return this;
    }

}
