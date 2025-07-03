package com.manje.modernJavaInAction.chap09.chainOfResponsibilityMain;

public class SpellCheckerProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }

}
