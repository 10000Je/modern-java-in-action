package com.manje.modernJavaInAction.chap15.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

// 자바 9의 Flow API를 이용한 발행-구독 모델 리액티브 프로그래밍
public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {

    private int value = 0;
    private String name;
    private List<Subscriber<? super Integer>> subscribers = new ArrayList<>();

    public static void main(String[] args) {
        SimpleCell c3 = new SimpleCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3); // c1 이 c3 를 구독함. c1에 변경이 가해지면 c3로 알림이 간다.

        c1.onNext(10); // C1의 값을 10으로 갱신, C1 -> C3
        c2.onNext(20); // C2의 값을 20으로 갱신, C2
    }

    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    public void subscribe(Consumer<? super Integer> onNext) {
        subscribers.add(new Subscriber<>() {

            @Override
            public void onComplete() {}

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onNext(Integer val) {
                onNext.accept(val);
            }

            @Override
            public void onSubscribe(Subscription s) {}

        });
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(subscriber -> subscriber.onNext(value));
    }

    @Override
    public void onNext(Integer newValue) {
        value = newValue; // 알림을 받으면 현재 값 갱신 (업스트림)
        System.out.println(name + ":" + value);
        notifyAllSubscribers(); // 다운스트림으로 알림 전송
    }

    @Override
    public void onComplete() {}

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onSubscribe(Subscription s) {}

}
