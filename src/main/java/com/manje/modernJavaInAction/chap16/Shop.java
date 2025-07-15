package com.manje.modernJavaInAction.chap16;

import java.util.Random;

import static com.manje.modernJavaInAction.chap16.Util.delay;
import static com.manje.modernJavaInAction.chap16.Util.format;

// 동기 API
public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    // 상품 이름을 받아서 상품의 가격을 받아오는 동기 API 메서드
    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code; // 이름:가격:할인코드
    }

    // 내부적으로 사용
    private double calculatePrice(String product) {
        delay();
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return name;
    }

}

