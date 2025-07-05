package com.manje.modernJavaInAction.chap09.factory;

import java.util.function.Supplier;

// 팩토리 디자인 패턴
public class FactoryMain {

    public static void main(String[] args) {
        Product p1 = ProductFactory.createProduct("loan");
        System.out.printf("p1: %s%n", p1.getClass().getSimpleName());

        Supplier<Product> loanSupplier = Loan::new;
        Product p2 = loanSupplier.get();
        System.out.printf("p2: %s%n", p2.getClass().getSimpleName());

        Product p3 = ProductFactory.createProductLambda("loan");
        System.out.printf("p3: %s%n", p3.getClass().getSimpleName());
    }

}
