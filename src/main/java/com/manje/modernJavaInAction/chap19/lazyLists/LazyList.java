package com.manje.modernJavaInAction.chap19.lazyLists;

import java.util.function.Predicate;
import java.util.function.Supplier;

// tail 을 게으르게 생성하는 게으른 리스트 구현체
public class LazyList<T> implements MyList<T> {

    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    // 현재 리스트를 게으르게 필터링 하는 메서드
    // head가 조건을 만족할 때 까지 요소를 스킵하며, 이후의 처리는 추가적인 필터링 함수를 체인하여 게으르게 진행한다.
    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
    }

}

