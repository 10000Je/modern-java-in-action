package com.manje.modernJavaInAction.chap13.compatability;

public interface Resizable extends Drawable {

    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);

    // 인터페이스에 새로운 추상 메서드를 추가해서 이 파일만 재컴파일 해도 기존에 컴파일된 코드들은 정상적으로 작동한다.
    // 다만 코드가 새로 추가된 추상 메서드를 호출하려 한다면 예외가 발생한다.
    // void setRelativeSize(int widthFactor, int heightFactor);

    // 이를 디폴트 메서드로 정의하면 모든 문제가 해결된다.
    default void setRelativeSize(int widthFactor, int heightFactor) {
        // a default implementation
    }

}

