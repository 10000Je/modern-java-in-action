package com.manje.modernJavaInAction.chap19.patternMatching;

import java.util.function.Function;
import java.util.function.Supplier;

public class PatternMatching {

    public static void main(String[] args) {
        simplify(); // 5 + 0 을 단순화한 5를 출력
        // 5 + 3 * 4 를 평가하는 예제
        Expr e = new BinOp("+", new Number(5), new BinOp("*", new Number(3), new Number(4)));
        Integer result = evaluate(e);
        System.out.println(e + " = " + result);
    }

    private static void simplify() {
        TriFunction<String, Expr, Expr, Expr> binopcase = (opname, left, right) -> {
            if ("+".equals(opname)) {
                if (left instanceof Number && ((Number) left).val == 0) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).val == 0) {
                    return left;
                }
            }
            if ("*".equals(opname)) {
                if (left instanceof Number && ((Number) left).val == 1) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).val == 1) {
                    return left;
                }
            }
            return new BinOp(opname, left, right);
        };
        Function<Integer, Expr> numcase = val -> new Number(val);
        Supplier<Expr> defaultcase = () -> new Number(0);

        Expr e = new BinOp("+", new Number(5), new Number(0)); // 5 + 0
        Expr match = patternMatchExpr(e, binopcase, numcase, defaultcase); // 5
        if (match instanceof Number) {
            System.out.println("Number: " + match);
        }
        else if (match instanceof BinOp) {
            System.out.println("BinOp: " + match);
        }
    }

    // Expr 을 받아, 타입에 따라 처리로직을 함수로 정의한 뒤, 이후 patternMatchExpr 메서드를 통해 단일 패턴 매칭
    // 단, 다단계 패턴 매칭을 위해 람다 표현식 내에서 조건에 따른 처리 로직을 정의한다.
    private static Integer evaluate(Expr e) {
        Function<Integer, Integer> numcase = val -> val;
        Supplier<Integer> defaultcase = () -> 0;
        TriFunction<String, Expr, Expr, Integer> binopcase = (opname, left, right) -> {
            if ("+".equals(opname)) {
                if (left instanceof Number && right instanceof Number) {
                    return ((Number) left).val + ((Number) right).val;
                }
                if (right instanceof Number && left instanceof BinOp) {
                    return ((Number) right).val + evaluate(left);
                }
                if (left instanceof Number && right instanceof BinOp) {
                    return ((Number) left).val + evaluate(right);
                }
                if (left instanceof BinOp && right instanceof BinOp) {
                    return evaluate(left) + evaluate(right);
                }
            }
            if ("*".equals(opname)) {
                if (left instanceof Number && right instanceof Number) {
                    return ((Number) left).val * ((Number) right).val;
                }
                if (right instanceof Number && left instanceof BinOp) {
                    return ((Number) right).val * evaluate(left);
                }
                if (left instanceof Number && right instanceof BinOp) {
                    return ((Number) left).val * evaluate(right);
                }
                if (left instanceof BinOp && right instanceof BinOp) {
                    return evaluate(left) * evaluate(right);
                }
            }
            return defaultcase.get();
        };

        return patternMatchExpr(e, binopcase, numcase, defaultcase);
    }

    // 조건에 따라 처리할 로직을 람다 표현식으로 받는 함수
    static <T> T MyIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

    // 람다 표현식으로 패턴 매칭을 흉내냄.
    // 다만 단일 패턴 매칭이 한계이므로, 다단계 패턴 매칭은 람다 표현식에서 담당해야함.
    static <T> T patternMatchExpr(Expr e, TriFunction<String, Expr, Expr, T> binopcase, Function<Integer, T> numcase, Supplier<T> defaultcase) {
        if (e instanceof BinOp) {
            return binopcase.apply(((BinOp) e).opname, ((BinOp) e).left, ((BinOp) e).right);
        }
        else if (e instanceof Number) {
            return numcase.apply(((Number) e).val);
        }
        else {
            return defaultcase.get();
        }
    }

}