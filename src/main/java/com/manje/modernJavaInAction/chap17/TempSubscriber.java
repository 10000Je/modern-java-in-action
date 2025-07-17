package com.manje.modernJavaInAction.chap17;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

// Publisher가 구독하는 Subscriber 인터페이스를 구현한 클래스
public class TempSubscriber implements Subscriber<TempInfo> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(TempInfo tempInfo) {
        System.out.println(tempInfo); // 알림을 처리
        subscription.request(1); // Subscription을 통해 역압력으로 알림을 요청
    }

    @Override
    public void onError(Throwable t) {
        System.err.println(t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
    }

}
