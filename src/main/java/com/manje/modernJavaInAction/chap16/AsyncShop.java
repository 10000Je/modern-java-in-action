package com.manje.modernJavaInAction.chap16;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.manje.modernJavaInAction.chap16.Util.delay;
import static com.manje.modernJavaInAction.chap16.Util.format;

// 비동기로 만든 API
public class AsyncShop {

    private final String name;
    private final Random random;

    public AsyncShop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }
    
    // calculatePrice 를 비동기로 시작한 뒤, 이를 CompletableFuture 로 반환
    public Future<Double> getPriceAsync(String product) {
    /*
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
          try {
            double price = calculatePrice(product);
            futurePrice.complete(price);
          } catch (Exception ex) {
            futurePrice.completeExceptionally(ex);
          }
        }).start();
        return futurePrice;
    */
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
    
    // 가격을 계산하는 동작을 흉내내는 메서드
    private double calculatePrice(String product) {
        delay();
        if (false) { // 디버깅용, true, false 로 바꿔가며 테스트
            throw new RuntimeException("product not available");
        }
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

}

