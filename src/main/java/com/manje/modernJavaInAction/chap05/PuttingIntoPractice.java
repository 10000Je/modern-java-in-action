package com.manje.modernJavaInAction.chap05;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> ans1 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(ans1);

        List<String> ans2 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());
        System.out.println(ans2);

        List<Trader> ans3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(ans3);

        List<String> ans4 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(toList());
        System.out.println(ans4);

        boolean ans5 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .anyMatch(city -> city.equals("Milan"));
        System.out.println(ans5);

        List<Transaction> ans6 = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .collect(toList());
        System.out.println(ans6);

        int ans7 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println(ans7);

        int ans8 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::min);
        System.out.println(ans8);
    }

}
