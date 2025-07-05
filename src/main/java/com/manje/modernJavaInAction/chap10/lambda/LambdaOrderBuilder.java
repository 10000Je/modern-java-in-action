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

package com.manje.modernJavaInAction.chap10.lambda;

import com.manje.modernJavaInAction.chap10.model.Order;
import com.manje.modernJavaInAction.chap10.model.Trade;

import java.util.function.Consumer;

// order( o -> o.buy( t -> t.stock( s -> s.symbol(...) ) ) ) 과 같이 호출
// 람다 표현식을 이용하여 함수의 중첩구조로 호출하고 있음.
public class LambdaOrderBuilder {

    private final Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept(builder); // builder에 함수 적용
        return builder.order; // 작업을 마친 빌더의 Order를 반환
    }

    public void forCustomer(String customer) {
        order.setCustomer(customer);
    }

    public void buy(Consumer<TradeBuilder> consumer) {
        trade(consumer, Trade.Type.BUY);
    }

    public void sell(Consumer<TradeBuilder> consumer) {
        trade(consumer, Trade.Type.SELL);
    }

    private void trade(Consumer<TradeBuilder> consumer, Trade.Type type) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType(type);
        consumer.accept(builder); // TradeBuilder 에 함수 적용
        order.addTrade(builder.trade);
    }

}
