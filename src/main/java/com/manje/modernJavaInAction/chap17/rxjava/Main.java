package com.manje.modernJavaInAction.chap17.rxjava;

import com.manje.modernJavaInAction.chap17.TempInfo;
import io.reactivex.Observable;

import static com.manje.modernJavaInAction.chap17.rxjava.TempObservable.getTemperature;

public class Main {

    public static void main(String[] args) {
        Observable<TempInfo> observable = getTemperature("New York");
        observable.blockingSubscribe(new TempObserver());
    }

}

