package com.manje.modernJavaInAction.chap10.grouping;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

import static com.manje.modernJavaInAction.chap10.grouping.Dish.menu;
import static com.manje.modernJavaInAction.chap10.grouping.GroupingBuilder.groupOn;
import static java.util.stream.Collectors.groupingBy;

public class Grouping {

    public static void main(String... args) {
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel2());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel3());
    }

    private static CaloricLevel getCaloricLevel(Dish dish) {
        if(dish.getCalories() <= 400) {
            return CaloricLevel.DIET;
        }
        else if(dish.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
        }
        else {
            return CaloricLevel.FAT;
        }
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel2() {
        return menu.stream().collect(
            twoLevelGroupingBy(Dish::getType, Grouping::getCaloricLevel)
        );
    }

    public static <A, B, T> Collector<T, ?, Map<A, Map<B, List<T>>>>
    twoLevelGroupingBy(Function<? super T, ? extends A> f1,  Function<? super T, ? extends B> f2) {
        return groupingBy(f1, groupingBy(f2));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel3() {
        // 그룹화 순서는 Type -> CaloricLevel 로 되는데, 코드가 평가되는 순서는 반대
        // 때문에, 그룹화 순서와는 반대로 메서드 체이닝이 이루어짐...비직관적
        Collector<? super Dish, ?, Map<Dish.Type, Map<CaloricLevel, List<Dish>>>> c
                = groupOn(Grouping::getCaloricLevel).after(Dish::getType).get();
        return menu.stream().collect(c);
    }

}
