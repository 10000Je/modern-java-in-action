package com.manje.modernJavaInAction.chap17.akka;

import java.util.concurrent.Flow.Publisher;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.AsPublisher;
import akka.stream.javadsl.JavaFlowSupport.Sink;
import akka.stream.javadsl.JavaFlowSupport.Source;
import com.manje.modernJavaInAction.chap17.TempInfo;
import com.manje.modernJavaInAction.chap17.TempSubscriber;
import com.manje.modernJavaInAction.chap17.TempSubscription;

// 교재에는 나오지 않은 akka 라이브러리를 이용한 리액티브 프로그래밍
public class Main {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("temp-info");
        Materializer materializer = ActorMaterializer.create(system);

        Publisher<TempInfo> publisher =
                Source.fromPublisher(getTemperatures("New York"))
                        .runWith(Sink.asPublisher(AsPublisher.WITH_FANOUT), materializer);
        publisher.subscribe(new TempSubscriber());

        try {
            Thread.sleep(10000L);
            system.terminate(); // Akka 라이브러리는 별도의 비데몬 스레드에서 발행을 시작하므로, 명시적으로 종료해야한다.
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }

}
