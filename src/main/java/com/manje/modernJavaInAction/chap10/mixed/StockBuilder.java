package com.manje.modernJavaInAction.chap10.mixed;

import com.manje.modernJavaInAction.chap10.model.Stock;
import com.manje.modernJavaInAction.chap10.model.Trade;

public class StockBuilder {

    private final TradeBuilder builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    StockBuilder(TradeBuilder builder, Trade trade, String symbol) {
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilder on(String market) {
        stock.setMarket(market);
        trade.setStock(stock);
        return builder;
    }

}
