package com.manje.modernJavaInAction.chap03.sorting;

import com.manje.modernJavaInAction.chap03.Apple;
import com.manje.modernJavaInAction.chap03.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class Sorting {

    public static void main(String... args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.RED),
                new Apple(120, Color.RED)
        ));
        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        inventory.set(1, new Apple(30, Color.RED));
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight() - a2.getWeight();
            }
        });
        System.out.println(inventory);

        inventory.set(1, new Apple(20, Color.RED));
        inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
        System.out.println(inventory);

        inventory.set(1, new Apple(10, Color.RED));
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);
    }

}
