package com.manje.modernJavaInAction.chap13.mostSpecific;

// B의 디폴트 메서드가 호출됨
// D가 호출하는건 A의 디폴트 메서드, B > A
public class E extends D implements A, B { }
