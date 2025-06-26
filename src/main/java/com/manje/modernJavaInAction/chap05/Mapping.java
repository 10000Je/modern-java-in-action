package com.manje.modernJavaInAction.chap05;

import java.util.Arrays;
import java.util.List;

import static com.manje.modernJavaInAction.chap05.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Mapping {

    public static void main(String... args) {
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);

        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

        words.stream()
                .flatMap(line -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);

    }

}
