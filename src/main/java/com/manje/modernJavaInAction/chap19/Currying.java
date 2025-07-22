package com.manje.modernJavaInAction.chap19;

import java.util.function.DoubleUnaryOperator;

public class Currying {

    public static void main(String[] args) {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        // 커링된 함수를 이용해 단일 인수로 함수 호출
        System.out.printf("24 °C = %.2f °F%n", convertCtoF.applyAsDouble(24));
        System.out.printf("US$100 = £%.2f%n", convertUSDtoGBP.applyAsDouble(100));
        System.out.printf("20 km = %.2f miles%n", convertKmtoMi.applyAsDouble(20));

        DoubleUnaryOperator convertFtoC = expandedCurriedConverter(-32, 5.0 / 9, 0);
        System.out.printf("98.6 °F = %.2f °C", convertFtoC.applyAsDouble(98.6));
    }

    // 인수를 여러 개 받는, 커링되지 않은 함수
    static double converter(double x, double y, double z) {
        return x * y + z;
    }

    // 두 인수를 받아 단일 인수를 받는 함수를 반환함 (커링)
    static DoubleUnaryOperator curriedConverter(double y, double z) {
        return (double x) -> x * y + z;
    }

    // 세 인수를 받아 단일 인수를 받는 함수를 반환함 (커링)
    static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
    }

}
