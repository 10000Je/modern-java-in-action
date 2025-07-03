package com.manje.modernJavaInAction.chap06.groupingTransactions;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class GroupingTransactions {

    public static List<Transaction> transactions = Arrays.asList(
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.EUR, 1500.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.USD, 2300.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.GBP, 9900.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.EUR, 1100.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.JPY, 7800.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.CHF, 6700.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.EUR, 5600.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.USD, 4500.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.CHF, 3400.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.GBP, 3200.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.USD, 4600.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.JPY, 5700.0),
            new Transaction(com.manje.modernJavaInAction.chap06.groupingTransactions.Currency.EUR, 6800.0)
    );

    public static void main(String... args) {
        groupImperatively();
        groupFunctionally();
    }

    // 명령형으로 그룹화하기
    private static void groupImperatively() {
        Map<com.manje.modernJavaInAction.chap06.groupingTransactions.Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for(Transaction transaction : transactions) {
            com.manje.modernJavaInAction.chap06.groupingTransactions.Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if(transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }
    
    // 함수형으로 그룹화하기
    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }

}
