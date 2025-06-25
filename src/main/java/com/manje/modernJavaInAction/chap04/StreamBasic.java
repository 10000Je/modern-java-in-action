package com.manje.modernJavaInAction.chap04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class StreamBasic {

    public static void main(String[] args) {
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);
        System.out.println("---");
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }

    // Java 7에서 사용하는 방식
    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if(dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        List<String> lowCaloricDishesNames = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d : lowCaloricDishes){
            lowCaloricDishesNames.add(d.getName());
        }
        return lowCaloricDishesNames;
    }
    
    // Java 8에서 사용하는 방식
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

}
