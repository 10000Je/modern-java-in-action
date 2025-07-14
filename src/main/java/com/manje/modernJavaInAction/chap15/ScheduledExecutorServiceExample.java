package com.manje.modernJavaInAction.chap15;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        work1();
        // sleep 으로 잠자기를 실행하지 않고 스케줄러를 이용하여 제출한 모습
        // work1 이 잠을 자 blocked 되면 스레드 풀에서 자원을 소비하므로 이러한 방식이 적절하다.
        scheduledExecutorService.schedule(ScheduledExecutorServiceExample::work2, 10, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown(); // non-blocking call, work2 는 다른 스레드에서 실행됨
    }

    public static void work1() {
        System.out.println("Hello from Work1!");
    }

    public static void work2() {
        System.out.println("Hello from Work2!");
    }

}
