package com.manje.modernJavaInAction.chap09.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
