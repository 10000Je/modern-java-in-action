package com.manje.modernJavaInAction.chap09.chainOfResponsibility;

public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

}
