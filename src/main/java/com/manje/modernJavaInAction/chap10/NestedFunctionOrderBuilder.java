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

import com.manje.modernJavaInAction.chap10.model.Order;
import com.manje.modernJavaInAction.chap10.model.Stock;
import com.manje.modernJavaInAction.chap10.model.Trade;

import java.util.stream.Stream;

// Order 는 주문자와 Trade의 리스트로 이루어져 있음
// Trade 는 주문 양, 주문 타입, 주식 종류, 가격으로 이루어져 있음
// Stock 은 심볼과 거래소로 이루어져 있다.
// 이러한 계층 구조를 이루는 객체를 함수의 중첩 호출을 통해 생성하고 있다.
public class NestedFunctionOrderBuilder {

    public static Order order(String customer, Trade... trades) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Trade.Type.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Trade.Type.SELL);
    }

    private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type buy) {
        Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setType(buy);
        trade.setStock(stock);
        trade.setPrice(price);
        return trade;
    }
    
    // 명시적으로 표현하기 위한 더미 메서드
    public static double at(double price) {
        return price;
    }

    public static Stock stock(String symbol, String market) {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    // 명시적으로 표현하기 위한 더미 메서드
    public static String on(String market) {
        return market;
    }

}
