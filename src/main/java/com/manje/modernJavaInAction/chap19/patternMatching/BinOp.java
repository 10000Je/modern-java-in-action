package com.manje.modernJavaInAction.chap19.patternMatching;

// 이항 연산자를 사용한 표현식을 표현하는 클래스
// 연산자, 좌항, 우항으로 구성된다.
public class BinOp extends Expr {

    String opname;
    Expr left, right;

    public BinOp(String opname, Expr left, Expr right) {
        this.opname = opname;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + " " + opname + " " + right + ")";
    }

}
