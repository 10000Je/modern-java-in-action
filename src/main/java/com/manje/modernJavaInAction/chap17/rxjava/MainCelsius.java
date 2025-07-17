package com.manje.modernJavaInAction.chap17.rxjava;

import com.manje.modernJavaInAction.chap17.TempInfo;
import io.reactivex.Observable;

import static com.manje.modernJavaInAction.chap17.rxjava.TempObservable.getCelsiusTemperatures;

public class MainCelsius {

    public static void main(String[] args) {
        Observable<TempInfo> observable = getCelsiusTemperatures("New York", "Chicago", "San Francisco");
        observable.blockingSubscribe(new TempObserver());
    }

}

