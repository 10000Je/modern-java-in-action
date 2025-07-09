package com.manje.modernJavaInAction.chap13.compatability;

import java.util.List;

public class Utils {

    public static void paint(List<Resizable> l) {
        l.forEach(r -> {
            r.setAbsoluteSize(42, 42);
        });

        // Resizable 을 수정하고, 아래의 메서드의 주석을 해제하면 AbstractMethodError가 발생한다.
        // 인터페이스를 수정해도 기존에 컴파일 된 코드는 작동하지만 새로운 메서드를 호출하려하면 오류가 발생...
        l.forEach(r -> { r.setRelativeSize(2, 2); });
    }

}
