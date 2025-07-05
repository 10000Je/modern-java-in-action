package com.manje.modernJavaInAction.chap09.factory;

import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

    static final private Map<String, Supplier<Product>> map = Map.of(
        "loan", Loan::new, "stock", Stock::new, "bond", Bond::new
    );

    public static Product createProduct(String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new RuntimeException("No such product " + name);
        }
    }

    // 람다 표현식으로 깔끔하게 단순화
    public static Product createProductLambda(String name) {
        Supplier<Product> p = map.get(name);
        if (p != null) {
            return p.get();
        }
        throw new RuntimeException("No such product " + name);
    }

}
