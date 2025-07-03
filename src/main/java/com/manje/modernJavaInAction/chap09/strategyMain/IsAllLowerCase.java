package com.manje.modernJavaInAction.chap09.strategyMain;

public class IsAllLowerCase implements ValidationStrategy {

    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }

}
