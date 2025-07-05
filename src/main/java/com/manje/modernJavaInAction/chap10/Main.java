/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.manje.modernJavaInAction.chap10;

import com.manje.modernJavaInAction.chap10.lambda.LambdaOrderBuilder;
import com.manje.modernJavaInAction.chap10.methodChaining.MethodChainingOrderBuilder;
import com.manje.modernJavaInAction.chap10.mixed.MixedBuilder;
import com.manje.modernJavaInAction.chap10.model.Order;
import com.manje.modernJavaInAction.chap10.model.Stock;
import com.manje.modernJavaInAction.chap10.model.Trade;

import static com.manje.modernJavaInAction.chap10.NestedFunctionOrderBuilder.*;

// 도메인 모델을 여러가지 DSL을 이용해서 사용하는 예제
public class Main {

    public static void main(String[] args) {
        plain();
        methodChaining();
        nestedFunction();
        lambda();
        mixed();
    }

    // DSL을 따로 사용하지 않고 객체를 생성...
    public static void plain() {
        Order order = new Order();
        order.setCustomer("BigBank");

        Trade trade1 = new Trade();
        trade1.setType(Trade.Type.BUY);

        Stock stock1 = new Stock();
        stock1.setSymbol("IBM");
        stock1.setMarket("NYSE");

        trade1.setStock(stock1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);
        order.addTrade(trade1);

        Trade trade2 = new Trade();
        trade2.setType(Trade.Type.SELL);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);
        order.addTrade(trade2);

        System.out.println("Plain:");
        System.out.println(order);
    }

    // 메서드 체이닝으로 구현한 DSL을 이용해서 객체를 생성...
    public static void methodChaining() {
        Order order = MethodChainingOrderBuilder.forCustomer("BigBank")
                .buy(80).stock("IBM").on("NYSE").at(125.00)
                .sell(50).stock("GOOGLE").on("NASDAQ").at(375.00)
                .end();

        System.out.println("Method chaining:");
        System.out.println(order);
    }

    // 중첩 함수로 구현한 DSL을 이용해서 객체를 생성...
    public static void nestedFunction() {
        Order order = NestedFunctionOrderBuilder.order("BingBank",
                NestedFunctionOrderBuilder.buy(80,
                        stock("IBM", on("NYSE")),
                        at(125.00)),
                NestedFunctionOrderBuilder.sell(50,
                        stock("GOOGLE", on("NASDAQ")),
                        at(375.00))
        );

        System.out.println("Nested Function:");
        System.out.println(order);
    }

    // 람다 표현식으로 구현한 DSL을 이용해서 객체를 생성...
    public static void lambda() {
        Order order = LambdaOrderBuilder.order(o -> {
            o.forCustomer("BigBank");
            o.buy(t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell(t -> {
                t.quantity(50);
                t.price(375.00);
                t.stock(s -> {
                    s.symbol("GOOGLE");
                    s.market("NASDAQ");
                });
            });
        });

        System.out.println("Lambda:");
        System.out.println(order);
    }

    // 람다 표현식과 메서드 체이닝 방식을 혼합하여 만든 DSL로 객체를 생성...
    public static void mixed() {
        Order order = MixedBuilder.forCustomer("BigBank",
                MixedBuilder.buy(t -> t.quantity(80)
                        .stock("IBM")
                        .on("NYSE")
                        .at(125.00)),
                MixedBuilder.sell(t -> t.quantity(50)
                        .stock("GOOGLE")
                        .on("NASDAQ")
                        .at(375.00))
        );

        System.out.println("Mixed:");
        System.out.println(order);
    }

}
