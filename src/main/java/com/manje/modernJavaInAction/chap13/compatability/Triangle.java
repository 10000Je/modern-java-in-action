package com.manje.modernJavaInAction.chap13.compatability;

/**
 * raoul-gabrielurma 작성 15/01/2014.
 */
public class Triangle implements Resizable {

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWidth(int width) {
    }

    @Override
    public void setHeight(int height) {
    }

    @Override
    public void setAbsoluteSize(int width, int height) {
    }

    @Override
    public void draw() {
    }

}