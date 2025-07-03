package com.manje.modernJavaInAction.chap02.filteringApples;

public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test (Apple apple) {
        return apple.getWeight() > 150;
    }
}
