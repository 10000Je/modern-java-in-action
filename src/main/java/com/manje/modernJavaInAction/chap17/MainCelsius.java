package com.manje.modernJavaInAction.chap17;

import java.util.concurrent.Flow.Publisher;

public class MainCelsius {

    public static void main(String[] args) {
        getCelsiusTemperatures("New York").subscribe(new TempSubscriber());
    }

    // 람다 표현식을 이용해 subscribe 함수의 시그니처를 작성하여 Publisher 객체를 작성,
    // 이후, 내부적으로 TempProcessor 객체를 생성해서 구독자를 등록한 뒤, 역압력 기법을 적용
    // 외부에서 반환된 Publisher를 이용해 구독자를 구독할 수 있다.
    public static Publisher<TempInfo> getCelsiusTemperatures(String town) {
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(processor, town));
        };
    }

}