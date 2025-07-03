package com.manje.modernJavaInAction.chap02.quiz;

import com.manje.modernJavaInAction.chap02.filteringApples.Apple;

public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String format (Apple apple) {
        String heavyOrLight = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + heavyOrLight + " " + apple.getColor() + " apple";
    }
}
