package com.manje.modernJavaInAction.chap05;

import java.util.Arrays;
import java.util.List;

import static com.manje.modernJavaInAction.chap05.Dish.menu;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Quiz {

    public static void main(String[] args) {
        // 5-1
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());
        System.out.println(dishes);

        //5-2
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> powers = numbers.stream()
                .map(i -> i*i)
                .collect(toList());
        System.out.println(powers);

        List<Integer> arr1 = Arrays.asList(1, 2, 3);
        List<Integer> arr2 = Arrays.asList(3, 4);
        List<int[]> pairs = arr1.stream()
                .flatMap(i -> arr2.stream().map(j -> new int[]{i, j}))
                .filter(arr -> (arr[0] + arr[1]) % 3 == 0)
                .collect(toList());
        pairs.forEach(arr -> System.out.printf("(%d, %d) ", arr[0], arr[1]));
        System.out.println();

        // 5-3
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);
    }

}
