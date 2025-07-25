package com.manje.modernJavaInAction.appc;

import static java.util.stream.Collectors.*;
import static com.manje.modernJavaInAction.chap06.Dish.menu;

import java.util.*;
import java.util.stream.*;

import com.manje.modernJavaInAction.appc.streamForker.Results;
import com.manje.modernJavaInAction.appc.streamForker.StreamForker;
import com.manje.modernJavaInAction.chap06.*;

public class StreamForkerExample {

    public static void main(String[] args) throws Exception {
        processMenu();
    }

    private static void processMenu() {
        Stream<Dish> menuStream = menu.stream();

        Results results = new StreamForker<Dish>(menuStream)
                .fork("shortMenu", s -> s.map(Dish::getName).collect(joining(", ")))
                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
                .fork("mostCaloricDish", s -> s.collect(
                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                        .get())
                .fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
                .getResults();

        String shortMeny = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");

        System.out.println("Short menu: " + shortMeny);
        System.out.println("Total calories: " + totalCalories);
        System.out.println("Most caloric dish: " + mostCaloricDish);
        System.out.println("Dishes by type: " + dishesByType);
    }
}
