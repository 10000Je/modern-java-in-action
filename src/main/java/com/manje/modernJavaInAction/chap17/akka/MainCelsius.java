package com.manje.modernJavaInAction.chap17.akka;

import java.util.concurrent.Flow.Publisher;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.AsPublisher;
import akka.stream.javadsl.JavaFlowSupport.Sink;
import akka.stream.javadsl.JavaFlowSupport.Source;
import com.manje.modernJavaInAction.chap17.TempInfo;
import com.manje.modernJavaInAction.chap17.TempProcessor;
import com.manje.modernJavaInAction.chap17.TempSubscriber;
import com.manje.modernJavaInAction.chap17.TempSubscription;

public class MainCelsius {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("temp-info");
        Materializer materializer = ActorMaterializer.create(system);

        Publisher<TempInfo> publisher =
                Source.fromPublisher(getCelsiusTemperatures("New York"))
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

    public static Publisher<TempInfo> getCelsiusTemperatures(String town) {
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(processor, town));
        };
    }

}

