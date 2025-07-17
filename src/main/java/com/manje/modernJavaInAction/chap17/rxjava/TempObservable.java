package com.manje.modernJavaInAction.chap17.rxjava;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.manje.modernJavaInAction.chap17.TempInfo;
import io.reactivex.Observable;

public class TempObservable {

    // 매 초마다 화씨 온도 정보를 방출하는 Observable 객체 생성
    public static Observable<TempInfo> getTemperature(String town) {
        return Observable.create(emitter -> Observable.interval(1, TimeUnit.SECONDS).subscribe(i -> {
            if (!emitter.isDisposed()) {
                if (i >= 5) {
                    emitter.onComplete();
                }
                else {
                    try {
                        emitter.onNext(TempInfo.fetch(town));
                    }
                    catch (Exception e) {
                        emitter.onError(e);
                    }
                }
            }
        }));
    }

    // 화씨 온도 정보를 방출하는 Observable 을 변환하여 섭씨 온도를 방출하는 Observable 반환
    public static Observable<TempInfo> getCelsiusTemperature(String town) {
        return getTemperature(town)
                .map(temp -> new TempInfo(temp.getTown(), (temp.getTemp() - 32) * 5 / 9));
    }
    
    // 온도가 음수인 것만 골라서 방출하는 Observable 반환
    public static Observable<TempInfo> getNegativeTemperature(String town) {
        return getCelsiusTemperature(town)
                .filter(temp -> temp.getTemp() < 0);
    }

    // 각 도시의 온도를 방출하는 여러 리액티브 스트림을 합쳐서 방출하는 Observable 반환
    public static Observable<TempInfo> getCelsiusTemperatures(String... towns) {
        return Observable.merge(Arrays.stream(towns)
                .map(TempObservable::getCelsiusTemperature)
                .collect(toList()));
    }

}

