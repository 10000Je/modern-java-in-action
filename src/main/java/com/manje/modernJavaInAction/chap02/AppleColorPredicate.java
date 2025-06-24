package com.manje.modernJavaInAction.chap02;

public class AppleColorPredicate implements ApplePredicate {
    @Override
    public boolean test (Apple apple) {
        return apple.getColor() == Color.GREEN;
    }
}
