package com.manje.modernJavaInAction.chap09.strategy;

public class Validator {

    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy v) {
        strategy = v;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }

}
