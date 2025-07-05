package com.manje.modernJavaInAction.chap10.lambda;

import com.manje.modernJavaInAction.chap10.model.Stock;

public class StockBuilder {

    final Stock stock = new Stock();

    public void symbol(String symbol) {
        stock.setSymbol(symbol);
    }

    public void market(String market) {
        stock.setMarket(market);
    }

}
