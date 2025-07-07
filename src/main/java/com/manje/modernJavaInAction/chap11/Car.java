package com.manje.modernJavaInAction.chap11;

import java.util.Optional;

public class Car {

    // IntelliJ 의 경우, 필드에 Optional 타입을 사용하면 노란 경고를 띄운다.
    // 왜냐하면 Optional 타입은 클래스의 필드로 사용될 목적으로 고안된 것은 아니기 때문이다.
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

}
