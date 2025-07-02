package com.manje.modernJavaInAction.chap09;

import java.util.function.Consumer;

public class OnlineBankingLambda {

    // 템플릿 메서드 패턴
    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello!"));
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

}
