package com.manje.modernJavaInAction.chap09.strategyMain;

public class IsNumeric implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }

}
