package com.manje.modernJavaInAction.chap03.sorting;

import com.manje.modernJavaInAction.chap03.Apple;

import java.util.Comparator;

public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight() - a2.getWeight();
    }
}
