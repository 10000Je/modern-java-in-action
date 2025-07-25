package com.manje.modernJavaInAction.chap16;

import static com.manje.modernJavaInAction.chap16.Util.delay;
import static com.manje.modernJavaInAction.chap16.Util.format;

public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }
    
    // Quote 값을 받아서, 할인된 가격을 받아오는 동기 API 메서드
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }

}
