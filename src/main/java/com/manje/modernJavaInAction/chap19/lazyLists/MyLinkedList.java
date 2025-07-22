package com.manje.modernJavaInAction.chap19.lazyLists;

import java.util.function.Predicate;

// 일반적으로 사용되는, 모든 노드가 메모리에 미리 존재해야하는 연결리스트 구현체
public class MyLinkedList<T> implements MyList<T> {

    final T head;
    final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ? this : p.test(head()) ? new MyLinkedList<>(head(), tail().filter(p)) : tail().filter(p);
    }

}
