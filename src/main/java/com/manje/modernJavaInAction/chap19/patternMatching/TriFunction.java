package com.manje.modernJavaInAction.chap19.patternMatching;

// 세 개의 인수 S, T, U를 받아 R을 반환하는 함수형 인터페이스
public interface TriFunction<S, T, U, R> {
    R apply(S s, T t, U u);
}