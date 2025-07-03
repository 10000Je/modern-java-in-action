package com.manje.modernJavaInAction.chap09.onlineBanking;

public abstract class OnlineBanking {

    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    abstract void makeCustomerHappy(Customer c);

}
