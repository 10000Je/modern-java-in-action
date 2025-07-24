package com.manje.modernJavaInAction.appa;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Repeatable 로 대상 어노테이션을 담고있는 컨테이너 어노테이션을 넘겨주어 반복 가능하게 지정한다.
@Repeatable(Authors.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

    String name();

}

