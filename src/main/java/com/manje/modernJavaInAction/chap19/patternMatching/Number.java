package com.manje.modernJavaInAction.chap19.patternMatching;

// 숫자를 표현하는 클래스
public class Number extends Expr {

    int val;

    public Number(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "" + val;
    }

}
