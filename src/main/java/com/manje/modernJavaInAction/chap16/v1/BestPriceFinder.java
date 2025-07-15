package com.manje.modernJavaInAction.chap16.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.manje.modernJavaInAction.chap16.v1.ExchangeService.Money;

public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
            //, new Shop("ShopEasy")
    );

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    // 순차 스트림
    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }
    
    // 병렬 스트림
    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }
    
    // CompletableFuture 를 이용한 메서드
    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(product), executor
                )).collect(Collectors.toList());

        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return prices;
    }
    
    // completeOnTimeout, orTimeout 을 이용해 타임 아웃을 설정한 메서드
    public List<String> findPricesInUSD(String product) {
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            // 예제 10-20 시작.
            // 아래 CompletableFuture::join와 호환되도록 futurePriceInUSD의 형식만 CompletableFuture로 바꿈.
            CompletableFuture<Double> futurePriceInUSD = CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                    .thenCombine(CompletableFuture.supplyAsync(() ->  ExchangeService.getRate(Money.EUR, Money.USD))
                                    // 자바 9에 추가된 타임아웃 관리 기능
                                    .completeOnTimeout(ExchangeService.DEFAULT_RATE, 1, TimeUnit.SECONDS),
                            (price, rate) -> price * rate)
                    .orTimeout(3, TimeUnit.SECONDS);
            priceFutures.add(futurePriceInUSD);
        }
        // 단점: 루프 밖에서 shop에 접근할 수 없으므로 아래 getName() 호출을 주석처리함.
        // so the getName() call below has been commented out.
        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join) // 앞에서 전체에 3초 타임아웃이 발생했다면 예외가 발생한다.
                .map(price -> /*shop.getName() +*/ " price is " + price)
                .collect(Collectors.toList());
        return prices;
    }
    
    // 자바 7의 Future 와 익명 클래스를 이용해서 구현한 메서드
    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                @Override
                public Double call() {
                    return ExchangeService.getRate(Money.EUR, Money.USD);
                }
            });
            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                @Override
                public Double call() {
                    try {
                        double priceInEUR = shop.getPrice(product);
                        return priceInEUR * futureRate.get();
                    }
                    catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(/*shop.getName() +*/ " price is " + priceFuture.get());
            }
            catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }

    // 별도의 루프에서 상점 이름에도 접근할 수 있게 데이터를 수정함.
    public List<String> findPricesInUSD2(String product) {
        List<CompletableFuture<String>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            // 루프에서 상점 이름에 접근할 수 있도록 동작을 추가함. 결과적으로 CompletableFuture<String> 인스턴스를 사용할 수 있음.
            CompletableFuture<String> futurePriceInUSD = CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                    .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)),
                            (price, rate) -> price * rate)
                    .thenApply(price -> shop.getName() + " price is " + price);
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = priceFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return prices;
    }

    // Stream과 CompletableFuture를 사용하면서 비동기로 값을 처리한다.
    public List<String> findPricesInUSD3(String product) {
        // 루프를 Stream 으로 변경, 단 join 은 모든 비동기 메서드가 시작되고 난 뒤에 해야 함
        Stream<CompletableFuture<String>> priceFuturesStream = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)),
                                (price, rate) -> price * rate)
                        .thenApply(price -> shop.getName() + " price is " + price));
        
        // 작업을 시작한 CompletableFuture 를 리스트로 모은 뒤, 각 요소에 join 을 호출해줌.
        List<CompletableFuture<String>> priceFutures = priceFuturesStream.collect(Collectors.toList());
        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return prices;
    }

}
