package com.manje.modernJavaInAction.chap04;

import java.util.List;

import static com.manje.modernJavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Quiz {

    // 4-1
    public static void main(String[] args) {
        List<String> highCaloricDishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());
        System.out.println(highCaloricDishes);
    }

}
