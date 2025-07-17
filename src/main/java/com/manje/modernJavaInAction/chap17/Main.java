package com.manje.modernJavaInAction.chap17;

import java.util.concurrent.Flow.Publisher;

// 메인 메서드가 종료되지 않는 이유는, Executors에서 사용한 스레드가 종료되지 않았기 때문이다.
// 종료시키기 위해선 데몬 스레드로 설정 한 뒤, 메인 메서드를 일정시간동안 blocking 시켜야한다.
public class Main {

    public static void main(String[] args) {
        getTemperatures("New York").subscribe(new TempSubscriber());
        try {
            Thread.sleep(10000L);
        }
        catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }

}

